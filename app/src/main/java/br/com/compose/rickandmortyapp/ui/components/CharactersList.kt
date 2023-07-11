package br.com.compose.rickandmortyapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.compose.rickandmortyapp.ui.viewmodel.ScreenState

@Composable
fun CharactersList(state: ScreenState, onLoadNextCharacters: () -> Unit) {

    val characters = state.items

    LazyColumn(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(characters.size) { index ->
            if ((index >= (characters.size - 1)) && state.endReached.not() && state.isLoading.not()) {
                onLoadNextCharacters()
            }
            val character = characters[index]
            CharacterItem(character = character)

        }
    }

}

@Preview
@Composable
fun CharactersListPreview() {

}