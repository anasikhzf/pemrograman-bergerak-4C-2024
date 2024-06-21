package com.bsmllh.implementasi6.data.remote

//mendefinisikan struktur respond yang diharapakn endpoint
data class APIResponse(
    val error: Boolean,
    val message: String,
    val count: Int,
    val data: List<PlayerData>
)

//titik akhir dari sebuah komunikasi antara aplikasi dengan layanan web api
