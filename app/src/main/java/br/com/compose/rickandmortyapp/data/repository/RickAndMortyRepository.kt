package br.com.compose.rickandmortyapp.data.repository

import br.com.compose.rickandmortyapp.data.models.CharactersResponse
import br.com.compose.rickandmortyapp.data.service.RickAndMortyService
import javax.inject.Inject
import retrofit2.Response


interface RickAndMortyRepository {
    suspend fun getCharactersByPage(
        page: Int = 1
    ): Response<CharactersResponse>
}

class RickAndMortyRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService
) : RickAndMortyRepository {
    override suspend fun getCharactersByPage(
        page: Int
    ): Response<CharactersResponse> =
        service.getCharactersByPage(page)
}