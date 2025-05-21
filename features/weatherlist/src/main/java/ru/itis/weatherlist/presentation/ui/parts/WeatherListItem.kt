package ru.itis.weatherlist.presentation.ui.parts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.domain.model.Weather
import ru.itis.weatherlist.R

@Composable
fun WeatherListItem(
    weather: Weather,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)) {
            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.headlineSmall
            )
            Row {
                Text(text = stringResource(R.string.temperature))
                Text(text = weather.temperature.toString())
            }
            Row {
                Text(text = stringResource(R.string.description))
                Text(text = weather.description)
            }
        }
    }
}