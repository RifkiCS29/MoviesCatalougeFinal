package com.rifki.jetpackpro.mymoviesfinal.di

import android.content.Context
import com.rifki.jetpackpro.mymoviesfinal.data.source.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): MovieAppRepository {
        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieAppRepository.getInstance(remoteDataSource)
    }
}