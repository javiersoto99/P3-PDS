package com.example.preguntadosicc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bottom nav bar

        //top nav bar
        val top_nav = findViewById<MaterialToolbar>(R.id.topAppBar)
        top_nav.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.logout -> {
                    finish()
                }
            }
            true
        }
    }


}