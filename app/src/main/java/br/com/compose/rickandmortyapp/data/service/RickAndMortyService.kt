package br.com.compose.rickandmortyapp.data.service

import br.com.compose.rickandmortyapp.data.models.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getAllCharacters(): Response<CharactersResponse>
}
