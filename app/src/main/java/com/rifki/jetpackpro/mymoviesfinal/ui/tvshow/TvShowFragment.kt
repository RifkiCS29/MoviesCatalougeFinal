package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentTvShowBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow.DetailTvShowActivity
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils.BEST_RATING
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource
import com.rifki.jetpackpro.mymoviesfinal.vo.Status

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

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()

            showLoading(true)
            viewModel.getTvShows(BEST_RATING).observe(viewLifecycleOwner, tvShowObserver)

            showRecyclerView()
        }
    }

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { listTvShows ->
        if ( listTvShows != null) {
            when ( listTvShows.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    tvShowAdapter.submitList( listTvShows.data)
                }
                Status.ERROR -> {
                    showLoading(false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowBinding = null
    }
}