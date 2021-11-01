package com.example.preguntadosicc.main.partidas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.OnClickListener
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.main.models.partidas.PartidasResponse
import com.example.preguntadosicc.main.models.partidas.PartidasViewModel
import com.example.preguntadosicc.main.models.preguntas.QuestionsViewModel

class PartidasActivasFragment: Fragment(), OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var partidasAdapter: PartidasRecyclerViewAdapter

    private val logInViewModel: LoginViewModel by activityViewModels()
    private val questionsViewModel : QuestionsViewModel by activityViewModels()
    private val partidasViewModel: PartidasViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_partidas_activas,container, false)
        logInViewModel.currentUser.observe(viewLifecycleOwner,{
            partidasViewModel.getPartidas(it.email)
        })

        partidasAdapter = PartidasRecyclerViewAdapter(this)
        recyclerView = view.findViewById(R.id.partidas_activas_recycler_view)
        recyclerView.adapter = partidasAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        partidasViewModel.myCurrentMatches.observe(viewLifecycleOwner,{
            partidasAdapter.setPartidasACtivas(it)
        })
        return view

    }

    override fun onClickItem(item: Any) {
        if (item is PartidasResponse){

            Toast.makeText(context, item.id.toString() + " " + item.creador, Toast.LENGTH_SHORT).show()

        }
    }
}