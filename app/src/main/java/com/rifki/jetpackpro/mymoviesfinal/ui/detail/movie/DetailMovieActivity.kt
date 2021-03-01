package com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rifki.jetpackpro.mymoviesfinal.BuildConfig
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailMovieEntity
import com.rifki.jetpackpro.mymoviesfinal.databinding.ActivityDetailMovieBinding
import com.rifki.jetpackpro.mymoviesfinal.databinding.ContentDetailMovieBinding
import com.rifki.jetpackpro.mymoviesfinal.utils.Convert
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailMovie

        setContentView(activityDetailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            if (movieId != null) {
                activityDetailMovieBinding.progressBar.visibility = View.VISIBLE
                activityDetailMovieBinding.contentMovie.visibility = View.INVISIBLE
                viewModel.setSelectedMovie(movieId)
                viewModel.getMovie().observe(this, { movie ->
                    activityDetailMovieBinding.progressBar.visibility = View.GONE
                    activityDetailMovieBinding.contentMovie.visibility = View.VISIBLE
                    populateMovie(movie)
                })
            }
        }
    }

    private fun populateMovie(movie: DetailMovieEntity) {
        with(contentDetailMovieBinding){
            tvTitle.text = movie.title
            tvTitle.isSelected = true
            tvTitle.isSingleLine = true
            tvDescription.text = movie.overview
            tvGenre.text = movie.genres.joinToString()
            tvGenre.isSelected = true
            tvGenre.isSingleLine = true
            tvRelease.text = Convert.convertStringToDate(movie.releaseDate)
            tvRating.text = movie.voteAverage.toString()
            tvDuration.text = Convert.runtimeToHours(movie.runtime)
            if (movie.tagline.isNullOrEmpty()) {
                tvTaglineTitle.visibility = View.GONE
            }
            tvQuoteValue.text = movie.tagline

            Picasso.get()
                    .load("${BuildConfig.URL_IMAGE}w185${movie.posterPath}")
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imagePoster)

            Picasso.get()
                    .load("${BuildConfig.URL_IMAGE}w500${movie.backdropPath}")
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imageBackdrop)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}