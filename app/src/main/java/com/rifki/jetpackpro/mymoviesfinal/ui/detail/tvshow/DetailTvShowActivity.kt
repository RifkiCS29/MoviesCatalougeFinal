package com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rifki.jetpackpro.mymoviesfinal.BuildConfig
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailTvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.databinding.ActivityDetailTvShowBinding
import com.rifki.jetpackpro.mymoviesfinal.databinding.ContentDetailTvShowBinding
import com.rifki.jetpackpro.mymoviesfinal.utils.Convert
import com.rifki.jetpackpro.mymoviesfinal.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var contentDetailTvShowBinding: ContentDetailTvShowBinding

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailTvShowBinding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        contentDetailTvShowBinding = activityDetailTvShowBinding.detailTvShow

        setContentView(activityDetailTvShowBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val tvShowId = extras.getString(EXTRA_TV_SHOW)
            if (tvShowId != null) {
                activityDetailTvShowBinding.progressBar.visibility = View.VISIBLE
                activityDetailTvShowBinding.contentTvShow.visibility = View.INVISIBLE
                viewModel.setSelectedTvShow(tvShowId)
                viewModel.getTvShow().observe(this, { tvShow ->
                    activityDetailTvShowBinding.progressBar.visibility = View.GONE
                    activityDetailTvShowBinding.contentTvShow.visibility = View.VISIBLE
                    populateTvShow(tvShow)
                })
            }
        }
    }

    private fun populateTvShow(tvShow: DetailTvShowEntity) {
        with(contentDetailTvShowBinding){
            tvName.text = tvShow.name
            tvName.isSelected = true
            tvName.isSingleLine = true
            tvDescription.text = tvShow.overview
            tvGenre.text = tvShow.genres.joinToString()
            tvGenre.isSelected = true
            tvGenre.isSingleLine = true
            tvRelease.text = Convert.convertStringToDate(tvShow.firstAirDate)
            tvRating.text = tvShow.voteAverage.toString()
            if (tvShow.tagline.isNullOrEmpty()) {
                tvTaglineTitle.visibility = View.GONE
            }
            tvQuoteValue.text = tvShow.tagline

            Picasso.get()
                .load("${BuildConfig.URL_IMAGE}w185${tvShow.posterPath}")
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(imagePoster)

            Picasso.get()
                .load("${BuildConfig.URL_IMAGE}w500${tvShow.backdropPath}")
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