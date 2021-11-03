package com.example.preguntadosicc.main.partidas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.Friend
import com.example.preguntadosicc.main.models.partidas.PartidasResponse

class AnterioresAdapter:RecyclerView.Adapter<AnterioresAdapter.AnterioresViewHolder>() {

    var partidas_anteriores = listOf<PartidasResponse>()

    inner class AnterioresViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val name = itemView.findViewById(R.id.nombre_partida_anterior) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnterioresAdapter.AnterioresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anteriores_view_holder, parent, false)
        return AnterioresViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnterioresAdapter.AnterioresViewHolder, position: Int) {
        val partida = partidas_anteriores[position]
        holder.name.text = partida.nombre


    }

    override fun getItemCount(): Int {
        return partidas_anteriores.size

    }
}

