package com.rifki.jetpackpro.mymoviesfinal.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentMovieBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie.DetailMovieActivity
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

            showLoading(true)
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                showLoading(false)
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()

            })

            showRecyclerView()
        }
    }

    private fun showRecyclerView() {
        binding?.let {
            with(it.rvMovies) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
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
        _fragmentMovieBinding = null
    }
}