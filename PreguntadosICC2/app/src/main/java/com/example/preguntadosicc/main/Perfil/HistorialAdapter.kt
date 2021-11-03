package com.example.preguntadosicc.main.Perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.Friend
import com.example.preguntadosicc.main.models.partidas.PartidasResponse
import com.example.preguntadosicc.navigation.Navigator

class HistorialAdapter: RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {
    lateinit var navigator : Navigator

    private var partidas = mutableListOf<PartidasResponse>()

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
        holder.detallesBtn.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return partidas.size
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }

}


