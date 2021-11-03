package com.example.preguntadosicc.navigation

import androidx.navigation.findNavController
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R

class Navigator(val activity: MainActivity?) {

    fun navigateToAmigos(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_inicioFragment_to_amigosFragment)

    }

    fun navigateToInvitaciones(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_inicioFragment_to_invitacionesFragment)

    }

    fun navigateToCrearPartida(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_inicioFragment_to_crearPartidaFragment)
    }

    fun navigateToEmpezarPartida(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_crearPartidaFragment_to_empezarPartidaFragment)
    }

    fun navitageToPregunta(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_partidasActivasFragment_to_preguntasFragment)
    }

    fun navigateToPartidasACtivas(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_inicioFragment_to_partidasActivasFragment)
    }

    fun navigateToVerPerfil(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_inicioFragment_to_verPerfilFragment)

    }

    fun navigateToDetalles(){
        activity?.findNavController(R.id.fragment)?.navigate(R.id.action_verPerfilFragment_to_detallesFragment)
    }

}