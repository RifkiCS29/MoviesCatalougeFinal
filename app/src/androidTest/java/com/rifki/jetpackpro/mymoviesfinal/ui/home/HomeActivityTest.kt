package com.rifki.jetpackpro.mymoviesfinal.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.ui.splashscreen.SplashScreenActivity
import com.rifki.jetpackpro.mymoviesfinal.utils.DataDummy
import com.rifki.jetpackpro.mymoviesfinal.utils.EspressoIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rifki.jetpackpro.mymoviesfinal.utils.Convert
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest{

    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTvShows = DataDummy.generateDummyTvShows()
    private val dummyDetailMovie = DataDummy.generateDetailMovie()
    private val dummyDetailTvShow = DataDummy.generateDetailTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(SplashScreenActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(withText(dummyDetailMovie.title)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyDetailMovie.overview)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyDetailMovie.voteAverage.toString())))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyDetailMovie.genres.joinToString())))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(Convert.convertStringToDate(dummyDetailMovie.releaseDate))))
        onView(withId(R.id.tvQuoteValue)).check(matches(isDisplayed()))
        onView(withId(R.id.tvQuoteValue)).check(matches(withText(dummyDetailMovie.tagline)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(Convert.runtimeToHours(dummyDetailMovie.runtime))))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.image_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(withText(dummyDetailTvShow.name)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyDetailTvShow.overview)))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(withText(dummyDetailTvShow.voteAverage.toString())))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(dummyDetailTvShow.genres.joinToString())))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(Convert.convertStringToDate(dummyDetailTvShow.firstAirDate))))
        onView(withId(R.id.tvQuoteValue)).check(matches(isDisplayed()))
        onView(withId(R.id.tvQuoteValue)).check(matches(withText(dummyDetailTvShow.tagline)))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister((EspressoIdlingResource.idlingResource))
    }
}