package com.example.noteapp.data.network

import retrofit2.http.GET

interface RickAndMortyApiService {

    @GET("character")
    fun getAllCharacters(): MainResultDto<CharacterDto>
}