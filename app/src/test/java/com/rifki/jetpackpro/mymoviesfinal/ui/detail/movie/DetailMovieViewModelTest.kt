package com.rifki.jetpackpro.mymoviesfinal.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailMovieEntity
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest{

    private lateinit var viewModel: DetailMovieViewModel

    private val dummyMovie = DataDummy.generateDetailMovie()
    private val dummyMovieId = dummyMovie.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<DetailMovieEntity>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(movieAppRepository)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<DetailMovieEntity>()
        movie.value = dummyMovie

        `when`(movieAppRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)
        viewModel.setSelectedMovie(dummyMovieId)
        val detailMovieEntity = viewModel.getMovie().value as DetailMovieEntity
        verify(movieAppRepository).getDetailMovie(dummyMovieId)
        assertNotNull(detailMovieEntity)

        assertEquals(dummyMovie.title, detailMovieEntity.title)
        assertEquals(dummyMovie.backdropPath, detailMovieEntity.backdropPath)
        assertEquals(dummyMovie.genres, detailMovieEntity.genres)
        assertEquals(dummyMovie.overview, detailMovieEntity.overview)
        assertEquals(dummyMovie.runtime, detailMovieEntity.runtime)
        assertEquals(dummyMovie.posterPath, detailMovieEntity.posterPath)
        assertEquals(dummyMovie.releaseDate, detailMovieEntity.releaseDate)
        assertEquals(dummyMovie.voteAverage.toString(), detailMovieEntity.voteAverage.toString())
        assertEquals(dummyMovie.tagline, detailMovieEntity.tagline)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}