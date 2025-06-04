package ru.itis.search.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.presentation.ui.parts.ErrorBottomSheet
import ru.itis.search.presentation.ui.parts.TopSearchBar
import ru.itis.search.presentation.ui.viewmodel.SearchViewModel
import ru.itis.search.presentation.ui.parts.WeatherResult

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.viewStates.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp)
            ) {

                TopSearchBar(
                    query = state.value.query,
                    onQueryChange = viewModel::onQueryChange,
                    onSearchClick = { viewModel.search() },
                    onBack = onBack
                )
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    when {
                        state.value.isLoading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        state.value.weather != null -> {
                            WeatherResult(state.value.weather!!)
                        }
                    }
                }

                state.value.error?.let {
                    ErrorBottomSheet(
                        message = it,
                        onDismiss = { viewModel.dismissError() }
                    )
                }
            }
        }
    )
}


