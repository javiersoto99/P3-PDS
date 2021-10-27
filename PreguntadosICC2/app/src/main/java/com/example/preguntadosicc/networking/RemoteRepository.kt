package com.example.preguntadosicc.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (isDebug)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return logging
}
//Este es el cliente que funciona por detras de retrofit
fun getOkClient(): OkHttpClient{
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(makeLoggingInterceptor(isDebug = true))
        .build()
}


fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {

    val retrofit = Retrofit.Builder().baseUrl("http://ec2-3-93-65-70.compute-1.amazonaws.com:4030/api/")
        .client(getOkClient()) // http://ec2-3-93-65-70.compute-1.amazonaws.com:4030/api/ http://10.0.2.2:4030/api/
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit
}