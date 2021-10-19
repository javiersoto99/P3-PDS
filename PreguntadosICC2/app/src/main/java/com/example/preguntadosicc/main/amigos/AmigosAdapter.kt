package com.example.preguntadosicc.main.amigos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R

class AmigosAdapter:
    RecyclerView.Adapter<AmigosAdapter.AmigosViewHolder>() {

    private var amigos = mutableListOf<Amigo>()

    inner class AmigosViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val name = itemView.findViewById(R.id.nombreAmigoUnidoTextView) as TextView
        val email = itemView.findViewById(R.id.EmailTextView) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmigosViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.amigos_view_holder, parent, false)
        return AmigosViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmigosViewHolder, position: Int) {
        val amigo = amigos[position]
        holder.name.text = amigo.name
        holder.email.text = amigo.email

    }

    override fun getItemCount(): Int {
        return amigos.size
    }

    fun setAmigos(amigos :MutableList<Amigo>){
        this.amigos = amigos
        this.notifyDataSetChanged()
    }

}