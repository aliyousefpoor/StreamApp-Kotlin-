package com.example.streamappkotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class SplashActivity : AppCompatActivity() {
    private lateinit var text: TextView
    private val splashTimeOut: Long = 3200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        text = findViewById(R.id.splashText)
        Handler().postDelayed(
            kotlinx.coroutines.Runnable {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, splashTimeOut
        )

//        val zoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in_animation)
//        val zoomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out_animation)
//        text.startAnimation(zoomOutAnimation)
//        text.startAnimation(zoomInAnimation)
        YoYo.with(Techniques.RubberBand)
            .delay(1000)
            .duration(700)
            .repeat(2)
            .playOn(text)
    }
}




