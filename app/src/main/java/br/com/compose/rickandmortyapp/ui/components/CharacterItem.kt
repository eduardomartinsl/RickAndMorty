package br.com.compose.rickandmortyapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        Box {
            val imageSize = 300.dp

            AsyncImage(
                modifier = Modifier.size(imageSize),
                model = character.imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = character.name
            )
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

/*
"info": {
    "count": 826,
    "pages": 42,
    "next": "https://rickandmortyapi.com/api/character/?page=2",
    "prev": null
  },
  "results": [
    {
      "id": 1,
      "name": "Rick Sanchez",
      "status": "Alive",
      "species": "Human",
      "type": "",
      "gender": "Male",
      "origin": {
        "name": "Earth",
        "url": "https://rickandmortyapi.com/api/location/1"
      },
      "location": {
        "name": "Earth",
        "url": "https://rickandmortyapi.com/api/location/20"
      },
      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
      "episode": [
        "https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2",
        // ...
      ],
      "url": "https://rickandmortyapi.com/api/character/1",
      "created": "2017-11-04T18:48:46.250Z"
    }
 */