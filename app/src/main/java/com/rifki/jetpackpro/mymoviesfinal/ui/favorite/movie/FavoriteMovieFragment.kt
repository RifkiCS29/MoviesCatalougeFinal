package com.rifki.jetpackpro.mymoviesfinal.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentFavoriteMovieBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie.DetailMovieActivity
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _fragmentFavoriteMovieBinding

    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            favoriteMovieAdapter = FavoriteMovieAdapter()

            showLoading(true)
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { favoriteMovies ->
                showLoading(false)
                favoriteMovieAdapter.submitList(favoriteMovies)
            })

            showRecyclerView()
        }
    }

    private fun showRecyclerView() {
        binding?.let {
            with(it.rvFavoriteMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }

        favoriteMovieAdapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movieId: String) {
                val intent = Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(DetailMovieActivity.EXTRA_MOVIE, movieId)
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
        _fragmentFavoriteMovieBinding = null
    }

}