package com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest{

    private lateinit var viewModel: DetailMovieViewModel

    private val dummyMovie = DataDummy.generateDetailMovie()
    private val dummyMovieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(movieAppRepository)
    }

    @Test
    fun getMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.generateDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieAppRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)
        viewModel.setSelectedMovie(dummyMovieId)
        viewModel.detailMovie.observeForever(observer)
        verify(observer).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.generateDetailMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieAppRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)
        viewModel.detailMovie =  movieAppRepository.getDetailMovie(dummyMovieId)
        viewModel.setFavoriteMovie()
        verify(movieAppRepository).setFavoriteMovie(movie.value?.data as MovieEntity, true)
        verifyNoMoreInteractions(observer)
    }
}