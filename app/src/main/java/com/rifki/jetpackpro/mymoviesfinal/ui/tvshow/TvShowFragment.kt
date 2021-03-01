package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentTvShowBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow.DetailTvShowActivity
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? =  null
    private val binding get() = _fragmentTvShowBinding
    private lateinit var tvShowAdapter : TvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvShowAdapter = TvShowAdapter()

            showLoading(true)
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                showLoading(false)
                tvShowAdapter.setTvShows(tvShows)
                tvShowAdapter.notifyDataSetChanged()
            })

            showRecyclerView()
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