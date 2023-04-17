package br.com.compose.rickandmortyapp.data.repository

import br.com.compose.rickandmortyapp.data.models.Character
import br.com.compose.rickandmortyapp.data.service.RickAndMortyService
import javax.inject.Inject
import retrofit2.Response


interface RickAndMortyRepository {

    suspend fun getAllCharacters(): Response<List<Character>>

}

class RickAndMortyRepositoryImpl @Inject constructor(private val service: RickAndMortyService) :
    RickAndMortyRepository {

    override suspend fun getAllCharacters(): Response<List<Character>> {
        return service.getAllCharacters()
    }
}