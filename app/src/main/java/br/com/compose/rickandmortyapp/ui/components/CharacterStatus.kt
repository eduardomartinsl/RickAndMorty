package br.com.compose.rickandmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.compose.rickandmortyapp.ui.theme.RickAndMortyAppTheme


// The status of the character ('Alive', 'Dead' or 'unknown').


@Composable
fun CharacterStatus() {

    Row(Modifier.padding(start = 4.dp)) {
        Box(
            modifier = Modifier
                .clip(shape = CircleShape)
                .align(Alignment.CenterVertically)
                .background(Color.Green)
                .size(10.dp)
        )
        Text(modifier = Modifier.padding(start = 4.dp), text = "Esse é o status")
    }
}

@Preview
@Composable
fun CharacterStatusPreview() {
    RickAndMortyAppTheme {
        Surface {
            CharacterStatus()
        }
    }
}