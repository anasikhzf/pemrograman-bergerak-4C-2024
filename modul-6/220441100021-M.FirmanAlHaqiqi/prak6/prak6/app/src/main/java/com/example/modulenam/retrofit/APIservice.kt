package com.example.modulenam.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface APIservice {
    @GET("tugas")
    fun getAllPlayer(): Call<APIresponse>
}