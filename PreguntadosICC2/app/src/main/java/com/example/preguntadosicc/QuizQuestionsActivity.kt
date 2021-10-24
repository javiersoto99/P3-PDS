package com.example.preguntadosicc

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.preguntadosicc.offline.Constants
import com.example.preguntadosicc.offline.Question

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener{

    private var mCurrentPosition:Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionsList = Constants.getQuestions()

        setQuestion()
        findViewById<TextView>(R.id.tv_option_one).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_two).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_three).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_four).setOnClickListener(this)

        findViewById<Button>(R.id.responderBtn).setOnClickListener(this)




    }
    private fun setQuestion(){

        val question =  mQuestionsList!!.get(mCurrentPosition - 1)

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            findViewById<Button>(R.id.responderBtn).text = "TERMINAR"
        }else{
            findViewById<Button>(R.id.responderBtn).text = "Responder"
        }

        findViewById<TextView>(R.id.CategoryTxt).text = question!!.category
        findViewById<TextView>(R.id.preguntaTxt).text = question!!.question
        findViewById<TextView>(R.id.tv_option_one).text = question!!.optionOne
        findViewById<TextView>(R.id.tv_option_two).text = question!!.optionTwo
        findViewById<TextView>(R.id.tv_option_three).text = question!!.optionThree
        findViewById<TextView>(R.id.tv_option_four).text = question!!.optionFour



    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,findViewById(R.id.tv_option_one))
        options.add(1,findViewById(R.id.tv_option_two))
        options.add(2,findViewById(R.id.tv_option_three))
        options.add(3,findViewById(R.id.tv_option_four))

        for(option in options){
            option.typeface = Typeface.DEFAULT
            option.setBackgroundColor(Color.parseColor("#E3E3E3"))

        }

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(findViewById(R.id.tv_option_one),1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(findViewById(R.id.tv_option_two),2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(findViewById(R.id.tv_option_three),3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(findViewById(R.id.tv_option_four),4)
            }
            R.id.responderBtn ->{
                if(mSelectedOptionPosition == 0){

                    mCurrentPosition++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }else ->{
                            Toast.makeText(this,"Has Termiando !",Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        Toast.makeText(this,"Respuesta Incorrecta!",Toast.LENGTH_SHORT).show()
                        answerView(mSelectedOptionPosition, "#FFF44336")
                    }
                    answerView(question.correctAnswer, "#FF8BC34A")


                    if(mCurrentPosition == mQuestionsList!!.size){
                        findViewById<Button>(R.id.responderBtn).text = "TERMINAR"
                    }else{
                        findViewById<Button>(R.id.responderBtn).text = "Siguente Pregunta"
                    }
                    mSelectedOptionPosition = 0

                }

            }
        }
    }
    private fun answerView(answer: Int, color: String){
        when(answer){
            1 -> {
                findViewById<TextView>(R.id.tv_option_one).setBackgroundColor(Color.parseColor(color))
            }
            2 -> {
                findViewById<TextView>(R.id.tv_option_two).setBackgroundColor(Color.parseColor(color))
            }
            3 -> {
                findViewById<TextView>(R.id.tv_option_three).setBackgroundColor(Color.parseColor(color))
            }
            4 -> {
                findViewById<TextView>(R.id.tv_option_four).setBackgroundColor(Color.parseColor(color))
            }
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTypeface(tv.typeface,Typeface.BOLD)

    }
}