package com.rifki.jetpackpro.mymoviesfinal.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.rifki.jetpackpro.mymoviesfinal.R
import com.rifki.jetpackpro.mymoviesfinal.databinding.ActivitySplashScreenBinding
import com.rifki.jetpackpro.mymoviesfinal.ui.home.HomeActivity
import com.rifki.jetpackpro.mymoviesfinal.utils.EspressoIdlingResource

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    companion object{
        private const val DELAY_MILLIS = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoMovie.animation = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        binding.myMovies.animation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            EspressoIdlingResource.decrement()
        }, DELAY_MILLIS)
    }
}