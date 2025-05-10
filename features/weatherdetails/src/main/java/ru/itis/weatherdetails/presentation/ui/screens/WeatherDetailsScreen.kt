package ru.itis.weatherdetails.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.presentation.ui.parts.ErrorBottomSheet
import ru.itis.weatherdetails.presentation.ui.parts.WeatherDetailsShimmer
import ru.itis.weatherdetails.presentation.ui.viewmodel.WeatherDetailsViewModel
import ru.itis.weatherdetails.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailsScreen(
    city: String,
    viewModel: WeatherDetailsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(city) {
        viewModel.loadWeatherDetails(city)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.value.weatherDetails?.cityName ?: city,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.default_description)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.value.isLoading -> WeatherDetailsShimmer()
                state.value.weatherDetails != null -> {
                    val weather = state.value.weatherDetails!!
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = weather.description.replaceFirstChar { it.uppercase() },
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 12.dp),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Row {
                            Text(
                                text = stringResource(R.string.temperature),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            Text(
                                text = weather.temperature.toString(),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        Row {
                            Text(
                                text = stringResource(R.string.feels_like),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            Text(
                                text = weather.feelsLike.toString(),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        Row {
                            Text(
                                text = stringResource(R.string.pressure),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            Text(
                                text = weather.pressure.toString(),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        Row {
                            Text(
                                text = stringResource(R.string.timezone),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )

                            Text(
                                text = weather.timezone.toString(),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
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
