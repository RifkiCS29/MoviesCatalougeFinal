package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentTvShowBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow.DetailTvShowActivity
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils.BEST_RATING
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils.DEFAULT
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? =  null
    private val binding get() = _fragmentTvShowBinding
    private lateinit var tvShowAdapter : TvShowAdapter
    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()

            showLoading(true)
            viewModel.getTvShows(DEFAULT).observe(viewLifecycleOwner, tvShowObserver)

            showRecyclerView()
        }
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { listTvShows ->
        if ( listTvShows != null) {
            when ( listTvShows) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    tvShowAdapter.submitList(listTvShows.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    binding?.lottieTvShow?.visibility = View.VISIBLE
                    binding?.tvNoData?.visibility = View.VISIBLE
                    Toast.makeText(context, "Failed to get Data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showRecyclerView() {
        binding?.let {
            with(it.rvTvShows) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }

        tvShowAdapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback {
            override fun onItemClicked(tvShowId: String) {
                val intent = Intent(context, DetailTvShowActivity::class.java).apply {
                    putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShowId)
                }
                startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sorting_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when(item.itemId) {
            R.id.action_default -> sort = DEFAULT
            R.id.action_best -> sort = BEST_RATING
            R.id.action_worst -> sort = SortUtils.WORST_RATING
        }
        viewModel.getTvShows(sort).observe(this, tvShowObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowBinding = null
    }
}