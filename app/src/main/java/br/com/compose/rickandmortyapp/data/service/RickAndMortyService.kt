package br.com.compose.rickandmortyapp.data.service

import br.com.compose.rickandmortyapp.data.models.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): Response<CharactersResponse>
}
