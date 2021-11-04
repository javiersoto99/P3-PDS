package com.example.preguntadosicc.main.Perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.partidas.PartidasResponse
import com.example.preguntadosicc.main.partidas.AnterioresAdapter

class DetallesAdapter: RecyclerView.Adapter<DetallesAdapter.DetallesViewHolder>() {

    inner class DetallesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val pregunta = itemView.findViewById(R.id.pregunta_tv) as TextView
        val respuesta = itemView.findViewById(R.id.respuesta_tv) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetallesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detalles_view_holder, parent, false)
        return DetallesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetallesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}


