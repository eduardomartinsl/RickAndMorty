package br.com.compose.rickandmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.compose.rickandmortyapp.R
import br.com.compose.rickandmortyapp.data.models.RMCharacter
import br.com.compose.rickandmortyapp.data.models.Status
import br.com.compose.rickandmortyapp.ui.theme.RickAndMortyAppTheme
import coil.compose.AsyncImage

@Composable
fun CharacterItem(character: RMCharacter) {
    Surface(
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Column {
            val imageSize = 400.dp

            AsyncImage(
                modifier = Modifier
                    .height(imageSize)
                    .fillMaxWidth()
                    .background(character.status.color),

                model = character.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.padding(start = 8.dp, top = 8.dp, 0.dp, 0.dp)) {
                Text(
                    text = character.name, style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = Bold
                    )
                )
                Row(Modifier.padding(top = 4.dp)) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clip(CircleShape)
                            .background(character.status.color)
                            .size(8.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = character.status.toString(),
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterItemPreview() {
    RickAndMortyAppTheme {
        Surface {
            CharacterItem(
                RMCharacter(
                    id = 1,
                    name = "Rick Sanchez",
                    status = Status.ALIVE,
                    imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
                )
            )
        }
    }
}