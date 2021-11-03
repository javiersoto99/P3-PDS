package com.example.preguntadosicc.main.partidas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.amigos.AmigosAdapter

class PartidasAnterioresFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnterioresAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_partidas_anteriores, container, false)

        adapter = AnterioresAdapter()
        recyclerView = view.findViewById(R.id.anteriores_RV)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)


        return view
    }


}