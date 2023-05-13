package br.com.compose.rickandmortyapp.data.models

import com.squareup.moshi.Json

data class CharactersResponse(

    @Json(name = "info")
    val info: Info,

    @Json(name = "results")
    val charactersList: List<RMCharacter>
)

