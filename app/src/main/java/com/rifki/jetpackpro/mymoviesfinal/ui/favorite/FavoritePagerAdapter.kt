package com.rifki.jetpackpro.mymoviesfinal.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.ui.favorite.movie.FavoriteMovieFragment
import com.rifki.jetpackpro.mymoviesfinal.ui.favorite.tvshow.FavoriteTvShowFragment


class FavoritePagerAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movies, R.string.tvshows)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size
}