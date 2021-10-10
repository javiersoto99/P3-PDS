package com.example.preguntadosicc.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.navigation.LoginNavigator
import com.example.preguntadosicc.navigation.Navigator

class InicioFragment : Fragment() {

    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        val amigosB = view.findViewById<Button>(R.id.AmigosBtn)
        amigosB.setOnClickListener {
            navigator.navigateToAmigos()
        }

        val invitacionesB = view.findViewById<Button>(R.id.InvitacionesBtn)
        invitacionesB.setOnClickListener {
            navigator.navigateToInvitaciones()
        }

        return view
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }


}