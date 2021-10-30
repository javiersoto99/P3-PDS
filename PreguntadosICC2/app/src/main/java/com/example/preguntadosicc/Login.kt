package com.example.preguntadosicc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        this.getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}