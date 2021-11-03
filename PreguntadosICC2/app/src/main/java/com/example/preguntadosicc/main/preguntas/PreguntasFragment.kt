package com.example.preguntadosicc.main.preguntas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preguntadosicc.OnClickListener
import com.example.preguntadosicc.R
import com.example.preguntadosicc.login.LoginViewModel
import com.example.preguntadosicc.main.models.partidas.PartidasViewModel
import com.example.preguntadosicc.main.models.preguntas.AlternativeResponse
import com.example.preguntadosicc.main.models.preguntas.AnswerInfo
import com.example.preguntadosicc.main.models.preguntas.QuestionsViewModel
import com.example.preguntadosicc.networking.QuestionRemoteRepository
import com.example.preguntadosicc.networking.getRetrofit
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreguntasFragment: Fragment(), OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var questionAdapter: PreguntasRecyclerViewAdapter



    private val logInViewModel: LoginViewModel by activityViewModels()
    private val questionsViewModel : QuestionsViewModel by activityViewModels()
    private val partidasViewModel : PartidasViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logInViewModel.getCurrentUser()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_preguntas, container, false)

        val headerQuestion = view.findViewById<TextView>(R.id.question_text)

        questionAdapter = PreguntasRecyclerViewAdapter(this)
        recyclerView = view.findViewById(R.id.alternatives_recycler_view)
        recyclerView.adapter = questionAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        questionsViewModel.myCurrentAlternatives.observe(viewLifecycleOwner,{
            questionAdapter.setAlternatives(it)
        })

        questionsViewModel.currentQuestion.observe(viewLifecycleOwner,{

            headerQuestion.text = it.pregunta
        })

        return view
    }

    override fun onClickItem(item: Any) {
        if (item is AlternativeResponse){
            questionsViewModel.currentQuestion.observe(viewLifecycleOwner,{

                println(item.id.toString() + "  " + it.id)
                validateQuestionAnswer(item.id, it.id)
            })

        }
    }

    fun validateQuestionAnswer(answer_id : Int, question_id : Int){

        val questionService = getRetrofit(okHttpClient = OkHttpClient()).create(
            QuestionRemoteRepository::class.java
        )

        partidasViewModel.currentMatch.observe(viewLifecycleOwner,{

            val match_id = it.id
            logInViewModel.currentUser.observe(viewLifecycleOwner,{
            val answer = AnswerInfo(answer_id,question_id, match_id, it.email)
            questionService.checkAnswer(answer).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t:Throwable){
                    println(
                        t.message + "GET validar respuesta"
                    )

                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {


                    if (response.code() == 200){
                        Toast.makeText(context, "Correcta " + answer.match_id + " " + answer.user_email, Toast.LENGTH_SHORT).show()
                    }
                    if(response.code() == 401){
                            Toast.makeText(context, "Incorrecta " + answer.match_id + " " + answer.user_email, Toast.LENGTH_SHORT).show()
                    }
                    if(response.code() == 500){
                        Toast.makeText(context, "Internal Server Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            })
        })





    }
}

