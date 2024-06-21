package com.bsmllh.implementasi6.data.remote

import retrofit2.Call
import retrofit2.http.GET

//mengakses  endpoint dari layanan api
interface APIService {
    @GET("tugas")
    fun getAllPlayer() : Call<APIResponse>
}