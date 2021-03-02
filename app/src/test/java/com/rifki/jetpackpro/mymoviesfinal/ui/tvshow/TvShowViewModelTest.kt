package com.rifki.jetpackpro.mymoviesfinal.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieAppRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DataDummy.generateDummyTvShows()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(movieAppRepository.getTvShows()).thenReturn(tvShows)
        val tvShow = viewModel.getTvShows().value
        Mockito.verify(movieAppRepository).getTvShows()
        assertNotNull(tvShow)
        assertEquals(4, tvShow?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }
}