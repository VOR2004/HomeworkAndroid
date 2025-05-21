package ru.itis.search.presentation.ui.parts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.domain.model.Weather
import ru.itis.search.R

@Composable
fun WeatherResult(weather: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = stringResource(R.string.city_name, weather.cityName),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Text(text = stringResource(R.string.temperature))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = weather.temperature.toString())
            }
            Row {
                Text(text = stringResource(R.string.description))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = weather.description)
            }
        }
    }
}