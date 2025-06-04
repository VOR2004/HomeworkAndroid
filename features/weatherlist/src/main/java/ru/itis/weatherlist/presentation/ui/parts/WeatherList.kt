package ru.itis.weatherlist.presentation.ui.parts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ru.itis.domain.model.Weather

@Composable
fun WeatherList(
    items: ImmutableList<Weather>,
    onItemClick: (Weather) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(top = 48.dp, start = 16.dp, end = 16.dp)
    ) {
        items(
            items = items,
            key = { it.cityName }
        ) { weather ->
            WeatherListItem(
                weather = weather,
                onClick = { onItemClick(weather) }
            )
        }
    }
}
