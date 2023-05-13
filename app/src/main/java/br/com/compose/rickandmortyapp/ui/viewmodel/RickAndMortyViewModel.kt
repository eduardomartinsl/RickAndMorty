package br.com.compose.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.compose.rickandmortyapp.data.models.RMCharacter
import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepository
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val moshi: Moshi
) : ViewModel() {

    @Inject
    lateinit var repository: RickAndMortyRepository

    private val _charactersList = MutableLiveData<List<RMCharacter>?>()
    val charactersList: LiveData<List<RMCharacter>?>
        get() = _charactersList

    fun getAllCharacters() =
        viewModelScope.launch { _charactersList.postValue(repository.getAllCharacters()) }
}