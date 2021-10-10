package com.example.preguntadosicc.main.amigos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.navigation.LoginNavigator
import com.example.preguntadosicc.navigation.Navigator


class AmigosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AmigosAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_amigos, container, false)

        adapter = AmigosAdapter()
        recyclerView = view.findViewById(R.id.amigos_recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }


}