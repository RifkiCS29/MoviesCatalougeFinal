package com.rifki.jetpackpro.mymoviesfinal.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.RemoteDataSource
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import com.rifki.jetpackpro.mymoviesfinal.utils.LiveDataTestUtil

class MovieAppRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieAppRepository = FakeMovieAppRepository(remote)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val detailMovieResponse = DataDummy.generateRemoteDetailMovie()

    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows()
    private val tvShowId = tvShowResponses[0].id
    private val detailTvShowResponse = DataDummy.generateRemoteDetailTvShow()

    @Test
    fun getMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                    .onMoviesReceived(movieResponses)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(movieAppRepository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size, movieEntities.size)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                    .onTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(movieAppRepository.getTvShows())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size, tvShowEntities.size)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailMovieCallback)
                    .onDetailMovieReceived(detailMovieResponse)
            null
        }.`when`(remote).getDetailMovie(eq(movieId), any())

        val detailMovieEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailMovie(movieId))
        verify(remote).getDetailMovie(eq(movieId), any())
        assertNotNull(detailMovieEntity)
        assertEquals(detailMovieResponse.id, detailMovieEntity.id)
        assertEquals(detailMovieResponse.title, detailMovieEntity.title)
        assertEquals(detailMovieResponse.overview, detailMovieEntity.overview)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadDetailTvShowCallback)
                    .onDetailTvShowReceived(detailTvShowResponse)
            null
        }.`when`(remote).getDetailTvShow(eq(tvShowId), any())

        val detailTvShowEntity = LiveDataTestUtil.getValue(movieAppRepository.getDetailTvShow(tvShowId))
        verify(remote).getDetailTvShow(eq(tvShowId), any())
        assertNotNull(detailTvShowEntity)
        assertEquals(detailTvShowResponse.id, detailTvShowEntity.id)
        assertEquals(detailTvShowResponse.name, detailTvShowEntity.name)
        assertEquals(detailTvShowResponse.overview, detailTvShowEntity.overview)
    }
}