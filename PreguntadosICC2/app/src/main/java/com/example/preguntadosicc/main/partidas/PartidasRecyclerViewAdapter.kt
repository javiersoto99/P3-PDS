package com.example.preguntadosicc.main.partidas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.IadapterView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.partidas.PartidasResponse

class PartidasRecyclerViewAdapter(override val onClickListener: PartidasActivasFragment):
    RecyclerView.Adapter<PartidasRecyclerViewAdapter.PartidasViewHolder>(), IadapterView{
        var data = listOf<PartidasResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidasViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partidas_activas_view_holder, parent,false)
        return PartidasViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartidasViewHolder, position: Int) {
        val item = data[position]

        holder.bindView(item)
        holder.itemView.setOnClickListener{
            onClickListener.onClickItem(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }



    fun setPartidasACtivas(alternatives: List<PartidasResponse>){
        this.data = alternatives
        this.notifyDataSetChanged()
    }

    fun delitem(pos: Int){
        // TODO: 18/6/2021
    }

    inner class PartidasViewHolder(private val view : View): RecyclerView.ViewHolder(view){

        fun bindView(item: PartidasResponse){

            val nombrePartida = view.findViewById<TextView>(R.id.nombre_partida_text_view)
            val creadorPartida = view.findViewById<TextView>(R.id.creador_partida_text_view)

            nombrePartida.text = item.nombre
            creadorPartida.text = item.creador
        }
    }
}