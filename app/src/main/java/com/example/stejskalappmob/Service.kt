package com.example.stejskalappmob

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("/api/games?")
    fun getCurrentData(): Call<List<Response>>
}