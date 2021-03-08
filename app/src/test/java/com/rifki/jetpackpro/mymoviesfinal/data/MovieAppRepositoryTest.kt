package com.rifki.jetpackpro.mymoviesfinal.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.LocalDataSource
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.RemoteDataSource
import com.rifki.jetpackpro.mymoviesfinal.utils.*
import com.rifki.jetpackpro.mymoviesfinal.vo.Resource
import org.mockito.Mockito.`when`

class MovieAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieAppRepository = FakeMovieAppRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val detailMovieResponse = DataDummy.generateRemoteDetailMovie()

    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].id
    private val detailTvShowResponse = DataDummy.generateRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies(SortUtils.DEFAULT)).thenReturn(dataSourceFactory)
        movieAppRepository.getMovies(SortUtils.DEFAULT)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovies(SortUtils.DEFAULT)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows(SortUtils.DEFAULT)).thenReturn(dataSourceFactory)
        movieAppRepository.getTvShows(SortUtils.DEFAULT)

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getTvShows(SortUtils.DEFAULT)
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = MutableLiveData<MovieEntity>()
        dummyDetailMovie.value = DataDummy.generateDetailMovie()
        `when`(local.getDetailMovieById(movieId)).thenReturn(dummyDetailMovie)

        val detailMovieEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailMovie(movieId))
        verify(local).getDetailMovieById((movieId))
        assertNotNull(detailMovieEntity)
        assertEquals(detailMovieResponse.id, detailMovieEntity.data?.id)
        assertEquals(detailMovieResponse.title, detailMovieEntity.data?.title)
        assertEquals(detailMovieResponse.overview, detailMovieEntity.data?.overview)
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTvShow = MutableLiveData<TvShowEntity>()
        dummyDetailTvShow.value = DataDummy.generateDetailTvShow()
        `when`(local.getDetailTvShowById(tvShowId)).thenReturn(dummyDetailTvShow)

        val detailTvShowEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailTvShow(tvShowId))
        verify(local).getDetailTvShowById(tvShowId)
        assertNotNull(detailTvShowEntity)
        assertEquals(detailTvShowResponse.id, detailTvShowEntity.data?.id)
        assertEquals(detailTvShowResponse.name, detailTvShowEntity.data?.name)
        assertEquals(detailTvShowResponse.overview, detailTvShowEntity.data?.overview)
    }

    @Test
    fun setFavoriteMovie() {
        movieAppRepository.setFavoriteMovie(DataDummy.generateDetailMovie(), true)
        verify(local).setFavoriteMovie(DataDummy.generateDetailMovie(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun setFavoriteTvShow() {
        movieAppRepository.setFavoriteTvShow(DataDummy.generateDetailTvShow(), true)
        verify(local).setFavoriteTvShow(DataDummy.generateDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}