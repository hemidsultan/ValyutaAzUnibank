package com.example.valyutaazunibank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class activity_splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


    }

    override fun onResume() {

        object : CountDownTimer(2000,1000){
            override fun onFinish() {
                var intent = Intent(this@activity_splash,MainActivity::class.java)
                startActivity(intent)
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()

        super.onResume()
    }
}
