package com.rifki.jetpackpro.mymoviesfinal.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailMovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailTvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.RemoteDataSource
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.*

class MovieAppRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieAppDataSource {

    companion object {
        @Volatile

        private var instance: MovieAppRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieAppRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieAppRepository(remoteData)
                }
    }

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val resultsMovieItem = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onMoviesReceived(movieResponses: List<ResultsMovieItem>) {
                val listMovies = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    with(response)  {
                        val movie = MovieEntity(
                            id,
                            posterPath,
                            title,
                            voteAverage
                        )
                        listMovies.add(movie)
                    }
                }
                resultsMovieItem.postValue(listMovies)
            }
        })
        return resultsMovieItem
    }

    override fun getTvShows(): LiveData<List<TvShowEntity>> {
        val resultsTvShowItem = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onTvShowsReceived(tvShowResponses: List<ResultsTvShowItem>) {
                val listTvShows = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    with(response) {
                        val tvShow = TvShowEntity(
                            id,
                            posterPath,
                            name,
                            voteAverage
                        )
                        listTvShows.add(tvShow)
                    }
                }
                resultsTvShowItem.postValue(listTvShows)
            }
        })
        return resultsTvShowItem
    }

    override fun getDetailMovie(movieId: String): LiveData<DetailMovieEntity> {
        val detailMovieItemResult = MutableLiveData<DetailMovieEntity>()

        remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadDetailMovieCallback {
            override fun onDetailMovieReceived(detailMovieResponse: DetailMovieResponse) {
                with(detailMovieResponse) {
                    val listGenres = ArrayList<String>()

                    for (genre in genres) {
                        listGenres.add(genre.name)
                    }

                    val detailMovieResult = DetailMovieEntity(
                            title = title,
                            backdropPath = backdropPath,
                            genres = listGenres,
                            id = id,
                            overview = overview,
                            runtime = runtime,
                            posterPath = posterPath,
                            releaseDate = releaseDate,
                            voteAverage = voteAverage,
                            tagline = tagline
                    )
                    detailMovieItemResult.postValue(detailMovieResult)
                }
            }
        })
        return detailMovieItemResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<DetailTvShowEntity> {
        val detailTvShowItemResult = MutableLiveData<DetailTvShowEntity>()

        remoteDataSource.getDetailTvShow(tvShowId, object : RemoteDataSource.LoadDetailTvShowCallback {
            override fun onDetailTvShowReceived(detailTvShowResponse: DetailTvShowResponse) {
                with(detailTvShowResponse) {
                    val listGenres = ArrayList<String>()

                    for (genre in genres) {
                        listGenres.add(genre.name)
                    }

                    val detailTvShowResult = DetailTvShowEntity(
                            backdropPath = backdropPath,
                            genres = listGenres,
                            id = id,
                            firstAirDate = firstAirDate,
                            overview = overview,
                            posterPath = posterPath,
                            voteAverage = voteAverage,
                            name = name,
                            tagline = tagline
                    )
                    detailTvShowItemResult.postValue(detailTvShowResult)
                }
            }
        })
        return detailTvShowItemResult
    }
}