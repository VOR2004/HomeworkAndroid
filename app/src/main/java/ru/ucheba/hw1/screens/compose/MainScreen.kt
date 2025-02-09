package ru.ucheba.hw1.screens.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R
import ru.ucheba.hw1.database.entities.MovieEntity
import ru.ucheba.hw1.viewmodel.AppViewModel
import ru.ucheba.hw1.screens.compose.ui.bottom_sheet.MovieBottomSheet
import ru.ucheba.hw1.screens.compose.ui.dialog.MovieCoverDialog
import ru.ucheba.hw1.screens.compose.ui.recycler.MovieItem

@Composable
fun MainScreen(
    vm: AppViewModel,
    onNavigateToSignInScreen: () -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var clickedMovie: MovieEntity? = null

    val userId = vm.getId()

    LaunchedEffect(userId) {
        if (userId != null) {
            vm.getMoviesForUser(userId)
        }
    }

    val movies: List<MovieEntity> by vm.movies.collectAsState()

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {
                    vm.logout()
                    onNavigateToSignInScreen()
                          },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = stringResource(R.string.exit),
                    modifier = Modifier.padding(end = 8.dp))
                Icon(
                    painterResource(R.drawable.baseline_logout_24),
                    contentDescription = stringResource(R.string.more_options)
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 32.dp)
        ) {
            items(movies, key = { it.id }) { movie ->
                MovieItem(
                    movie = movie,
                    onDelete = {
                        vm.deleteMovie(movie)
                    },
                    onClick = {
                        showDialog = true
                        clickedMovie = movie
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            TextButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                onClick = { showBottomSheet = true },
                shape = RoundedCornerShape(48.dp),
            ) {
                Icon(
                    painterResource(R.drawable.baseline_add_circle_24),
                    contentDescription = stringResource(R.string.more_options),
                    modifier = Modifier.size(48.dp),
                )
            }
        }
        if (showBottomSheet) {
            MovieBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                vm = vm
            )
        }

        if (showDialog) {
            clickedMovie?.let {
                MovieCoverDialog(
                    onDismissRequest = { showDialog = false },
                    movie = it
                )
            }
        }
    }
}