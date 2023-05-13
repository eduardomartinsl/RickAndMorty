package br.com.compose.rickandmortyapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.compose.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import br.com.compose.rickandmortyapp.ui.viewmodel.RickAndMortyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RickAndMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getAllCharacters()
        viewModel.charactersList.observe(this) {
            println(it)
        }

        setContent {
            RickAndMortyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}