package com.example.retrofit.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("tugas")
    fun getAllPlayer() : Call<APIResponse>
}