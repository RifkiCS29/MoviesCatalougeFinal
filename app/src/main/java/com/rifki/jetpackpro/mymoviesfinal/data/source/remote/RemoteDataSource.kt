package com.rifki.jetpackpro.mymoviesfinal.data.source.remote

import android.util.Log
import com.rifki.jetpackpro.mymoviesfinal.BuildConfig
import com.rifki.jetpackpro.mymoviesfinal.api.ApiConfig
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.*
import com.rifki.jetpackpro.mymoviesfinal.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource()
                }
    }

    fun getMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getMovies(BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    callback.onMoviesReceived(it.results)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getTvShows(BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                response.body()?.results?.let {
                    callback.onTvShowsReceived(it)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailMovie(movieId: String, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailMovie(movieId, BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                response.body()?.let {
                    callback.onDetailMovieReceived(it)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getDetailTvShow(tvShowId: String, callback: LoadDetailTvShowCallback) {
        EspressoIdlingResource.increment()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId, BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(call: Call<DetailTvShowResponse>, response: Response<DetailTvShowResponse>) {
                response.body()?.let {
                    callback.onDetailTvShowReceived(it)
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviesCallback {
        fun onMoviesReceived(movieResponses : List<ResultsMovieItem>)
    }

    interface LoadTvShowsCallback {
        fun onTvShowsReceived(tvShowResponses: List<ResultsTvShowItem>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse)
    }

    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShowResponse: DetailTvShowResponse)
    }
}