package com.example.preguntadosicc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.preguntadosicc.main.Perfil.VerPerfilFragment
import com.example.preguntadosicc.main.amigos.AmigosFragment
import com.example.preguntadosicc.main.fragments.InicioFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        this.getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



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
    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment, fragment)
        commit()
    }
}