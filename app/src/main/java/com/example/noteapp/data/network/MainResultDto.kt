package com.example.noteapp.data.network

data class MainResultDto<T>(
    val info: InfoDto? = null,
    val result: List<T>
)