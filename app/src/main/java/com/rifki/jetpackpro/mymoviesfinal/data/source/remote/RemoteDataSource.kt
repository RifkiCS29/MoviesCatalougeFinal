package com.rifki.jetpackpro.mymoviesfinal.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getMovies(): LiveData<ApiResponse<List<ResultsMovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<ResultsMovieItem>>>()
        val client = ApiConfig.getApiService().getMovies(BuildConfig.TMDB_API_KEY)

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>)
            {
                val dataMovies = response.body()?.results
                resultMovies.value =
                    if (dataMovies != null) ApiResponse.Success(dataMovies)
                    else ApiResponse.Empty
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                resultMovies.value = ApiResponse.Error(t.message.toString())
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<List<ResultsTvShowItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShows = MutableLiveData<ApiResponse<List<ResultsTvShowItem>>>()

        val client = ApiConfig.getApiService().getTvShows(BuildConfig.TMDB_API_KEY)
        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>)
            {
                val dataTvShows = response.body()?.results
                resultTvShows.value =
                    if (dataTvShows != null) ApiResponse.Success(dataTvShows)
                    else ApiResponse.Empty
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                resultTvShows.value = ApiResponse.Error(t.message.toString())
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShows
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<ResultsMovieItem>> {
        EspressoIdlingResource.increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<ResultsMovieItem>>()
        val client = ApiConfig.getApiService().getDetailMovie(movieId, BuildConfig.TMDB_API_KEY)

        client.enqueue(object : Callback<ResultsMovieItem> {
            override fun onResponse(
                call: Call<ResultsMovieItem>,
                response: Response<ResultsMovieItem>)
            {
                val detailMovies = response.body()
                resultDetailMovie.value =
                    if (detailMovies != null) ApiResponse.Success(detailMovies)
                    else ApiResponse.Empty
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsMovieItem>, t: Throwable) {
                resultDetailMovie.value = ApiResponse.Error(t.message.toString())
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailMovie
    }

    fun getDetailTvShow(tvShowId: String): LiveData<ApiResponse<ResultsTvShowItem>> {
        EspressoIdlingResource.increment()
        val resultDetailTvShow =  MutableLiveData<ApiResponse<ResultsTvShowItem>>()
        val client = ApiConfig.getApiService().getDetailTvShow(tvShowId, BuildConfig.TMDB_API_KEY)

        client.enqueue(object : Callback<ResultsTvShowItem> {
            override fun onResponse(
                call: Call<ResultsTvShowItem>,
                response: Response<ResultsTvShowItem>)
            {
                val detailTvShow = response.body()
                resultDetailTvShow.value =
                    if (detailTvShow != null) ApiResponse.Success(detailTvShow)
                    else ApiResponse.Empty
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResultsTvShowItem>, t: Throwable) {
                resultDetailTvShow.value = ApiResponse.Error(t.message.toString())
                Log.d(TAG, "onFailure :${t.message}" )
                EspressoIdlingResource.decrement()
            }
        })
        return resultDetailTvShow
    }
}