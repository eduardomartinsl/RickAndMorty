package br.com.compose.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.compose.rickandmortyapp.data.models.CharactersResponse
import br.com.compose.rickandmortyapp.data.models.RMCharacter
import br.com.compose.rickandmortyapp.data.paginator.MainPaginator
import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.Serializable
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private var savedStateHandle: SavedStateHandle,
    private val repository: RickAndMortyRepository
) : ViewModel() {

    companion object {
        const val SCREEN_STATE_KEY = "SCREEN_STATE_KEY"
    }

    val screenStateFlow = savedStateHandle.getStateFlow(SCREEN_STATE_KEY, ScreenState())

    private val paginator = MainPaginator<Int, RMCharacter>(
        initialKey = screenStateFlow.value.page,
        onLoadUpdated = setIsLoading(false),
        onRequest = handleRequestResponse(),
        getNextKey = { screenStateFlow.value.page + 1 },
        onError = setError(),
        onSuccess = handleSuccess()
    )

    init {
        loadNextCharacters()
    }

    private fun handleSuccess(): suspend (Items: List<RMCharacter>, newKey: Int) -> Unit =
        { items, nextPage ->


            savedStateHandle[SCREEN_STATE_KEY] = screenStateFlow.value.copy(
                items = screenStateFlow.value.items + items,
                page = nextPage,
                endReached = items.isEmpty()
            )
        }

    private fun setError(): suspend (Throwable?) -> Unit =
        {
            savedStateHandle[SCREEN_STATE_KEY] =
                screenStateFlow.value.copy(error = it?.localizedMessage)
        }

    private fun handleRequestResponse(): suspend (nextKey: Int) -> Response<CharactersResponse> =
        { nextPage -> repository.getCharactersByPage(nextPage) }

    private fun setIsLoading(isLoading: Boolean): (Boolean) -> Unit =
        { savedStateHandle[SCREEN_STATE_KEY] = screenStateFlow.value.copy(isLoading = isLoading) }

    fun loadNextCharacters() = viewModelScope.launch {
        paginator.loadNextPage()
    }
}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<RMCharacter> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
) : Serializable