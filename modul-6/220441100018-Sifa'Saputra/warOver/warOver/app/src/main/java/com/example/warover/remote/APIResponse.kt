package com.example.warover.remote

data class APIResponse(
    val error: Boolean,
    val message: String,
    val count: Int,
    val data: List<PlayerData>
)
