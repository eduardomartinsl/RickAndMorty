package br.com.compose.rickandmortyapp.data.models

import com.squareup.moshi.Json

data class RMCharacter(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "status")
    val status: Status,

    @Json(name = "image")
    val imageUrl: String
)

enum class Status() {
    DEAD,
    ALIVE,
    UNKNOWN;

    private fun formattedStatus(): String =
        name.substring(0, 1) + name.substring(1).lowercase()

    override fun toString(): String = formattedStatus()
}