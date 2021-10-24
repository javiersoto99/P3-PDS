package com.example.preguntadosicc.offline

object Constants {

    fun getQuestions() : ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(1,"Computacion", "Que hace la siguente operacion: A & B", "A OR B", "A AND B", "B OR A", "A NOT B", 2)
        questionsList.add(que1)
        val que2 = Question(2, "Quimica", "Cual es el simbolo del Cobre en la tabla periodica?", "Au", "Co", "Mg","Cu",4)
        questionsList.add(que2)
        val que3 = Question(3, "Plan Comun","Cuantos creditos tiene el ramo Algebra e Introduccion al Calculo?", "6","4","9", "20",3)
        questionsList.add(que3)
        val que4 = Question(4,"Matematica", "Cual es la derivada de la siguente funcion: 2x^2-ln(x)", "2x - 1/x", "4x - 1/x", "4x - e^2","2x-x",2)
        questionsList.add(que4)
        val que5 = Question(5,"Biologia", "Cual de las siguentes opciones no es una caracteristica de TODOS los microorganismos: ", "Comunicacion","Metabolismo", "Reproducción", "Evolución",1)
        questionsList.add(que5)

        return questionsList
    }

}