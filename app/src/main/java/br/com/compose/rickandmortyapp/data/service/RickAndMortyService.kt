package br.com.compose.rickandmortyapp.data.service

import br.com.compose.rickandmortyapp.data.models.Character
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getAllCharacters(): Response<List<Character>>
}
