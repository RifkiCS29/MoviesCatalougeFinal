package com.rifki.jetpackpro.mymoviesfinal.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentFavoriteTvShowBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow.DetailTvShowActivity
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory


class FavoriteTvShowFragment : Fragment() {

    private var _fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _fragmentFavoriteTvShowBinding

    private lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentFavoriteTvShowBinding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            favoriteTvShowAdapter = FavoriteTvShowAdapter()

            showLoading(true)
            viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, { favoriteTvShows ->
                showLoading(false)
                favoriteTvShowAdapter.submitList(favoriteTvShows)
            })

            showRecyclerView()
        }
    }

    private fun showRecyclerView() {
        binding?.let {
            with(it.rvFavoriteTvShows) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = favoriteTvShowAdapter
            }
        }

        favoriteTvShowAdapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback {
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
        _fragmentFavoriteTvShowBinding = null
    }
}