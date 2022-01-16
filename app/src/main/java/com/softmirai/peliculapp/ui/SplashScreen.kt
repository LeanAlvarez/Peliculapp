package com.softmirai.peliculapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.softmirai.peliculapp.R
import com.softmirai.peliculapp.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //cargo la animacion
        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        val imagen = binding.ivLogoQV
        imagen.startAnimation(animacion)


        //creo el intent para cambiar de pantalla
        val intent = Intent(this, MainActivity::class.java)

        //implemento los metodos para la animacion
        animacion.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })


    }
}