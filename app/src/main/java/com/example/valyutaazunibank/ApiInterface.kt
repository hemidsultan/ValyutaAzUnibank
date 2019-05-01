package com.example.valyutaazunibank

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("live?")

    fun getApi(@Query("access_key") key:String , @Query("currencies") sym : String) : Call<Data>
}