package com.rifki.jetpackpro.mymoviesfinal.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.rifki.jetpackpro.mymoviesfinal.data.MovieAppRepository
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest{
    
    
    private lateinit var viewModel: DetailTvShowViewModel

    private val dummyTvShow = DataDummy.generateDetailTvShow()
    private val dummyTvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAppRepository: MovieAppRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowEntity>>

    @Before
    fun setup() {
        viewModel = DetailTvShowViewModel(movieAppRepository)
    }

    @Test
    fun getTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.generateDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieAppRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)
        viewModel.setSelectedTvShow(dummyTvShowId)
        viewModel.detailTvShow.observeForever(observer)
        verify(observer).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.generateDetailTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyDetailTvShow

        `when`(movieAppRepository.getDetailTvShow(dummyTvShowId)).thenReturn(tvShow)
        viewModel.detailTvShow = movieAppRepository.getDetailTvShow(dummyTvShowId)
        viewModel.setFavoriteTvShow()
        verify(movieAppRepository).setFavoriteTvShow(tvShow.value?.data as TvShowEntity, true)
        verifyNoMoreInteractions(observer)
    }
}