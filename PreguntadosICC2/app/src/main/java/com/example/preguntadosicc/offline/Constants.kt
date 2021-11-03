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
        val que6 = Question(6,"Matematica","Cual es el valor de Cos(45°)?","0","1/2","1","√2/2",4)
        questionsList.add(que6)
        val que7 = Question(7,"Computacion","Cual de las siguente Estructuras de Datos es de tipo LIFO?", "Stack", "Lista Ligada", "Grafo", "Array",1)
        questionsList.add(que7)
        val que8 = Question(8,"Quimica","Cual es el numero de Avogadro?","3.14", "6.02 x 10^23", "0.082 x 10^-1", "1.23 x 10^5",2)
        questionsList.add(que8)
        val que9 = Question(9,"Quimica", "Cual es el pH del agua pura?", "14", "1", "7", "4",3)
        questionsList.add(que9)
        val que10 = Question(10,"Biologia", "Cual es el FENOTIPO de un organismo?", "Cualidades fisicas notorias", "Factores hereditarios", "Arbol Genealogico", "Especie",1)
        questionsList.add(que10)
        val que11 = Question(11,"Plan Comun","Que profesor NO dicta el curso de Programacion?", "Matias Recabarren", "Claudio Alvarez", "Andres Howard", "Jessica San Martin", 4)
        questionsList.add(que11)
        val que12 = Question(12, "Biologia", "Que son las histonas?", "Proteinas cargadas positivamente", "Tipo de celula", "Un carbohidrato sequencial", "Una enzima", 1)
        questionsList.add(que12)
        val que13 = Question(13, "Plan Comun", "Que curso es pre-requisito de Algebra Lineal?","EDO","AEIC", "Calculo 1", "Termodinamica", 2)
        questionsList.add(que13)
        val que14 = Question(14, "Matematica", "Cual es una propiedad de la Matriz Transpuesta?","(AB)t = BtA", "(rA)t = rA", "(At)t=A","(A + B)t = A + B",3)
        questionsList.add(que14)
        val que15 = Question(15,"Computacion", "Luego de realizar la implementación de una tarea solicitada, usted necesitara guardar el trabajo realizado cual de los siguientes comandos de GIT utilizaría?", "Pull", "Commit", "Merge","NA", 2)
        questionsList.add(que15)

        return questionsList
    }

}