package com.example.stejskalappmob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gr.net.maroulis.library.EasySplashScreen
import kotlinx.android.synthetic.main.activity_nacitani.*

class Nacitani : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nacitani)
        gamepad.alpha = 0f
        gamepad.animate().setDuration(3000).alpha(1f).withEndAction{
            val zmena = Intent(this, MainActivity::class.java)
            startActivity(zmena)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }


    }
}