package com.rifki.jetpackpro.mymoviesfinal.ui.movie

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
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.databinding.FragmentMovieBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie.DetailMovieActivity
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils.BEST_RATING
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils.DEFAULT
import com.rifki.jetpackpro.mymoviesfinal.utils.SortUtils.WORST_RATING
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource
import com.rifki.jetpackpro.mymoviesfinal.vo.Status

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

            showLoading(true)
            viewModel.getMovies(DEFAULT).observe(viewLifecycleOwner, movieObserver)

            showRecyclerView()
        }
    }

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { listMovies ->
        if (listMovies != null) {
            when (listMovies.status) {
                Status.LOADING -> showLoading(true)
                Status.SUCCESS -> {
                    showLoading(false)
                    movieAdapter.submitList(listMovies.data)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sorting_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when(item.itemId) {
            R.id.action_default -> sort = DEFAULT
            R.id.action_best -> sort = BEST_RATING
            R.id.action_worst -> sort = WORST_RATING
        }
        viewModel.getMovies(sort).observe(this, movieObserver)
        item.isChecked = true

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMovieBinding = null
    }
}