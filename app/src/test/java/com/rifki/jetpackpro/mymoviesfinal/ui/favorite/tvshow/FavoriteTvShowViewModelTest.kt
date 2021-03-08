package com.rifki.jetpackpro.mymoviesfinal.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowViewModelTest  {

    private lateinit var viewModel: FavoriteTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = FavoriteTvShowViewModel(movieAppRepository)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(4)
        val tvShows =  MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyMovies

        `when`(movieAppRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getFavoriteTvShows().value
        verify(movieAppRepository).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(4, tvShowEntities?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteTvShow() {
        viewModel.setFavoriteTvShow(DataDummy.generateDetailTvShow())
        verify(movieAppRepository).setFavoriteTvShow(DataDummy.generateDetailTvShow(), true)
        verifyNoMoreInteractions(movieAppRepository)
    }
}