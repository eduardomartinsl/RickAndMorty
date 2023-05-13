package br.com.compose.rickandmortyapp.data.paginator

import br.com.compose.rickandmortyapp.data.models.CharactersResponse
import br.com.compose.rickandmortyapp.data.models.RMCharacter
import retrofit2.Response

class MainPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Response<CharactersResponse>,
    private inline val getNextKey: suspend (List<RMCharacter>) -> Key,
    private inline val onSuccess: suspend (Items: List<RMCharacter>, newKey: Key) -> Unit,
    private inline val onError: suspend (Throwable?) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest) return

        isMakingRequest = true
        onLoadUpdated(true)

        val result = onRequest(currentKey)

        result.body()?.let {
            onSuccess(it.charactersList, currentKey)
            getNextKey(it.charactersList)
        } ?: onError
    }


    override fun reset() {
        currentKey = initialKey
    }
}