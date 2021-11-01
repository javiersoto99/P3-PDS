package com.example.preguntadosicc.main.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.preguntadosicc.Login
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.main.models.preguntas.QuestionsViewModel
import com.example.preguntadosicc.navigation.LoginNavigator
import com.example.preguntadosicc.navigation.Navigator

class InicioFragment : Fragment() {

    private val logInViewModel: LoginViewModel by activityViewModels()
    private val questionViewModel: QuestionsViewModel by activityViewModels()


    lateinit var navigator : Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigator(activity as MainActivity)
        logInViewModel.getCurrentUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        val welcome = view.findViewById<TextView>(R.id.HolaTxt)

        logInViewModel.currentUser.observe(viewLifecycleOwner,{
            welcome.text = "Bienvenido " + it.username
        })

        val amigosB = view.findViewById<Button>(R.id.AmigosBtn)
        amigosB.setOnClickListener {
            navigator.navigateToAmigos()
        }

        val invitacionesB = view.findViewById<Button>(R.id.InvitacionesBtn)
        invitacionesB.setOnClickListener {
            navigator.navigateToInvitaciones()
        }

        val crearPartidaBtn = view.findViewById<Button>(R.id.CrearPartidaBtn)
        crearPartidaBtn.setOnClickListener {
            navigator.navigateToCrearPartida()
        }


        val cerrarSesionB = view.findViewById<Button>(R.id.CerrarSesionBtn)

        cerrarSesionB.setOnClickListener {
            logInViewModel.logOut()
            val intent = Intent(activity, Login::class.java)
            this.startActivity(intent)

        }

        val activas = view.findViewById<Button>(R.id.ActivasBtn)
        activas.setOnClickListener{
            navigator.navigateToPartidasACtivas()
        }

        return view
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }


}