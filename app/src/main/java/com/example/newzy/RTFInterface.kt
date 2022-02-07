package com.example.newzy

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val baseUrl = "https://newsapi.org/"
interface RTFInterface{

    @GET("v2/everything?sortBy=publishedAt&apiKey=941e63334bdd4136b665074937be6cf6")
    fun getNews(@Query("q")q: String): Call<News>

}

object NewsServices{
    val instanceRTF: RTFInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        instanceRTF = retrofit.create(RTFInterface::class.java)
    }
}