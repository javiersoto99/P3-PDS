package com.example.preguntadosicc.main.preguntas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.IadapterView
import com.example.preguntadosicc.R
import com.example.preguntadosicc.main.models.preguntas.AlternativeResponse

class PreguntasRecyclerViewAdapter(override val onClickListener: PreguntasFragment):
    RecyclerView.Adapter<PreguntasRecyclerViewAdapter.QuestionViewHolder>(), IadapterView{

    var dataQuestionOptions = listOf<AlternativeResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preguntas_view_holder, parent,false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val item = dataQuestionOptions[position]

        holder.bindViewAlternatives(item)
        holder.itemView.setOnClickListener{
            onClickListener.onClickItem(item)
        }
    }

    override fun getItemCount(): Int {
        return dataQuestionOptions.size
    }



    fun setAlternatives(alternatives: List<AlternativeResponse>){
        this.dataQuestionOptions = alternatives
        this.notifyDataSetChanged()
    }

    fun delitem(pos: Int){
        // TODO: 18/6/2021
    }

    inner class QuestionViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bindViewAlternatives(item: AlternativeResponse){

            val questionAlternativeBody = view.findViewById<TextView>(R.id.answer_body)
            questionAlternativeBody.text = item.body
        }

    }
}