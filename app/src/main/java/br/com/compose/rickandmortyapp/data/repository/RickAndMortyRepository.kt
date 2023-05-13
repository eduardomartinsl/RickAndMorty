package br.com.compose.rickandmortyapp.data.repository

import br.com.compose.rickandmortyapp.data.models.CharactersResponse
import br.com.compose.rickandmortyapp.data.models.RMCharacter
import br.com.compose.rickandmortyapp.data.service.RickAndMortyService
import com.squareup.moshi.Moshi
import javax.inject.Inject
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


interface RickAndMortyRepository {
    suspend fun getAllCharacters(): List<RMCharacter>

}

class RickAndMortyRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService, moshi: Moshi
) : RickAndMortyRepository {

    private val charactersResponseAdapter = moshi.adapter(CharactersResponse::class.java)

    override suspend fun getAllCharacters(): List<RMCharacter> {

        var allCharacters = mutableListOf<RMCharacter>()

        coroutineScope {
            launch {
                service.getAllCharacters().let { response ->
                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            println(it)
                            allCharacters = it.results
                        }
                    }
                }
            }
        }
        return allCharacters
    }
}