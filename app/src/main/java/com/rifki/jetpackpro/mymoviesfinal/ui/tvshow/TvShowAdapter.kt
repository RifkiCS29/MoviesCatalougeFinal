package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifki.jetpackpro.mymoviesfinal.BuildConfig
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.databinding.ItemMovieTvShowBinding
import com.squareup.picasso.Picasso

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listTvShows = ArrayList<TvShowEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setTvShows(tvShows: List<TvShowEntity>){
        if (tvShows.isNullOrEmpty()) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieTvShowBinding = ItemMovieTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShows[position])
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowViewHolder(private val binding: ItemMovieTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                tvTitle.text = tvShow.name
                tvRating.text = tvShow.voteAverage.toString()

                Picasso.get()
                        .load("${BuildConfig.URL_IMAGE}w185${tvShow.posterPath}")
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .into(imgPoster)

                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tvShow.id)
                }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvShowId: String)
    }
}