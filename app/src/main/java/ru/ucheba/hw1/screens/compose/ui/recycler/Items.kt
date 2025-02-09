package ru.ucheba.hw1.screens.compose.ui.recycler

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.ucheba.hw1.R
import ru.ucheba.hw1.database.entities.MovieEntity


@Composable
fun MovieItem(movie: MovieEntity, onDelete: () -> Unit, onClick: () -> Unit) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            onDelete()
            return@rememberSwipeToDismissBoxState it ==
                    SwipeToDismissBoxValue.StartToEnd
        },
        positionalThreshold = { it * .75f }
    )
    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier,
        enableDismissFromEndToStart = false,
        backgroundContent = {},
        content = {
            Item(movie, onClick)
        }
    )
}

@Composable
fun Item(movie: MovieEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (movie.uri != null) {
                AsyncImage(
                    model = movie.uri,
                    contentDescription = stringResource(R.string.movie_cover),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = stringResource(R.string.movie_cover_no_image),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Text(text = movie.movieName, style = MaterialTheme.typography.headlineSmall)
            Text(text = "${stringResource(R.string.year_eng)} ${movie.year}")
            Text(text = "${stringResource(R.string.rating_eng)} ${movie.rating}")
        }
    }
}