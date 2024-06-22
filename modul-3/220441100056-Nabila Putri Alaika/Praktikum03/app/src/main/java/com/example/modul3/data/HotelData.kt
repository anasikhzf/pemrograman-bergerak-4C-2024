package com.example.modul3.data

import android.net.wifi.WifiManager.LocalOnlyHotspotReservation

data class HotelData(
    val name: String,
    val description: String,
    val image: Int,
    val review: String,
    val reservation: String,
    val destinationName: String,
    val destinationImage: Int,
    val destinationLocation: String
)
