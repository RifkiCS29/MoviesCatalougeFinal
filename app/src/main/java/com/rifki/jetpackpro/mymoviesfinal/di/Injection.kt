package com.rifki.jetpackpro.mymoviesfinal.di

import android.content.Context
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.LocalDataSource
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.room.MovieAppDatabase
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.RemoteDataSource
import com.rifki.jetpackpro.mymoviesfinal.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieAppRepository {
        val database = MovieAppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieAppDao())
        val appExecutors = AppExecutors()

        return MovieAppRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}