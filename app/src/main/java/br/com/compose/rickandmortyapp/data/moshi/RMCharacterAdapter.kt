package br.com.compose.rickandmortyapp.data.moshi

import br.com.compose.rickandmortyapp.data.models.Status
import com.squareup.moshi.FromJson
import javax.inject.Singleton

@Singleton
class RMCharacterAdapter {

    companion object {
        private const val ALIVE = "ALIVE"
        private const val DEAD = "DEAD"
        private const val UNKNOWN = "UNKNOWN"
    }

    @FromJson
    fun fromJson(status: String): Status {

        return when (status.uppercase()) {
            ALIVE -> Status.ALIVE
            DEAD -> Status.DEAD
            UNKNOWN -> Status.ALIVE
            else -> throw IllegalArgumentException("Invalid value for $status")
        }
    }
}