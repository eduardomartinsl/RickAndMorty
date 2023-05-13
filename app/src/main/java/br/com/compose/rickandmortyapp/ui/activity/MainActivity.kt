package br.com.compose.rickandmortyapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.com.compose.rickandmortyapp.ui.components.CharactersList
import br.com.compose.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import br.com.compose.rickandmortyapp.ui.viewmodel.RickAndMortyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RickAndMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyAppTheme {
                Surface {

                    val screenState by viewModel.screenStateFlow.collectAsState()


                    CharactersList(
                        state = screenState,
                        onLoadNextCharacters = {
                            viewModel.loadNextCharacters()
                        })
                }
            }
        }
    }
}