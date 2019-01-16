package com.jvtnascimento.chucknorrisjokes.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jvtnascimento.chucknorrisjokes.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        android.os.Handler().postDelayed(
            { startSignInActivity() },
            2000
        )
    }

    private fun startSignInActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
