package ru.itis.weatherlist.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.BottomAppBar
import androidx.compose.ui.Modifier
import ru.itis.domain.model.Weather
import ru.itis.presentation.ui.parts.ErrorBottomSheet
import ru.itis.weatherlist.presentation.ui.parts.AddCityDialog
import ru.itis.weatherlist.presentation.ui.parts.WeatherList
import ru.itis.weatherlist.presentation.ui.parts.WeatherListShimmer
import ru.itis.weatherlist.presentation.ui.viewmodel.WeatherViewModel
import androidx.compose.material3.Icon
import ru.itis.weatherlist.R

@Composable
fun MainScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    onItemClick: (Weather) -> Unit,
    onSearchClick: () -> Unit
) {
    val state = viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(64.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = onSearchClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.default_description)
                        )
                    }

                    IconButton(
                        onClick = { viewModel.showAddCityDialog() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.default_description)
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.value.isLoading -> WeatherListShimmer()
                state.value.weatherList.isNotEmpty() -> WeatherList(
                    items = state.value.weatherList,
                    onItemClick = onItemClick
                )
            }

            if (state.value.showAddCityDialog) {
                AddCityDialog(
                    onAddCity = { viewModel.addCity(it) },
                    onDismiss = { viewModel.hideAddCityDialog() }
                )
            }

            state.value.error?.let { error ->
                ErrorBottomSheet(
                    message = error,
                    onDismiss = { viewModel.clearError() }
                )
            }
        }
    }
}
