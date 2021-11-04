package com.example.preguntadosicc.main.Perfil

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.invitaciones.Match
import com.example.preguntadosicc.main.models.partidas.PartidasResponse
import com.example.preguntadosicc.main.partidas.AnterioresAdapter

class DetallesAdapter: RecyclerView.Adapter<DetallesAdapter.DetallesViewHolder>() {

    private var answers = mutableListOf<Answer>()

    inner class DetallesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val pregunta = itemView.findViewById(R.id.pregunta_tv) as TextView
        val respuesta = itemView.findViewById(R.id.respuesta_tv) as TextView
        val linearLayout = itemView.findViewById(R.id.detalles_linear_layout) as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetallesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detalles_view_holder, parent, false)
        return DetallesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetallesViewHolder, position: Int) {
        val answer = answers[position]

        holder.pregunta.text = answer.question_body
        holder.respuesta.text = answer.answer_body
        if (answer.is_correct){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FF8BC34A"))
        }
        else{
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFF44336"))
        }
    }

    override fun getItemCount(): Int {
        return answers.size
    }

    fun setRequests(answers : MutableList<Answer>){
        this.answers = answers
        this.notifyDataSetChanged()
    }


}


