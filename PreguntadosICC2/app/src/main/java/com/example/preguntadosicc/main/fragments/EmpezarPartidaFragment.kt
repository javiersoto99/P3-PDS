package com.example.preguntadosicc.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.amigos.AmigosAdapter


class EmpezarPartidaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: invitarAmigosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_empezar_partida, container, false)
        adapter = invitarAmigosAdapter()
        recyclerView = view.findViewById(R.id.amigosRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }


}