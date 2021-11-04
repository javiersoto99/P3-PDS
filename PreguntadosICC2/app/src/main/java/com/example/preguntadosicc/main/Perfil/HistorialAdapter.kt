package com.example.preguntadosicc.main.Perfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.IadapterView
import com.example.preguntadosicc.MainActivity
import com.example.preguntadosicc.OnClickListener
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.invitaciones.Match
import com.example.preguntadosicc.main.models.Friend
import com.example.preguntadosicc.main.models.partidas.PartidasResponse
import com.example.preguntadosicc.navigation.Navigator

class HistorialAdapter(override val onClickListener: VerPerfilFragment):
    RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>(), IadapterView {
    lateinit var navigator : Navigator

    private var partidas = mutableListOf<Match>()

    inner class HistorialViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val resultado = itemView.findViewById(R.id.resutado_tv) as TextView
        val ganador = itemView.findViewById(R.id.ganador_tv) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.historial_view_holder, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val partida = partidas[position]

        holder.resultado.text = partida.nombre
        holder.ganador.text = partida.ganador

        holder.itemView.setOnClickListener {
            onClickListener.onClickItem(partida)
        }

    }

    override fun getItemCount(): Int {
        return partidas.size
    }

    fun setRequests(solicitudes : MutableList<Match>){
        this.partidas = solicitudes
        this.notifyDataSetChanged()
    }

    fun setNavigator(activity: MainActivity?) {
        navigator = Navigator(activity)
    }

}


