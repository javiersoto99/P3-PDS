package com.example.preguntadosicc.main.Perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.Friend

class HistorialAdapter: RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    private var partidas = mutableListOf<String>()

    inner class HistorialViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val resultado = itemView.findViewById(R.id.resutado_tv) as TextView
        val detallesBtn = itemView.findViewById<Button>(R.id.detalles_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.historial_view_holder, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return partidas.size
    }

}


