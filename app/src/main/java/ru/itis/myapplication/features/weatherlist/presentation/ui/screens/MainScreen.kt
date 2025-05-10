import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.myapplication.R
import ru.itis.myapplication.features.weatherlist.domain.model.Weather
import ru.itis.myapplication.features.weatherlist.presentation.ui.parts.AddCityDialog
import ru.itis.myapplication.core.presentation.ui.parts.ErrorBottomSheet
import ru.itis.myapplication.features.weatherlist.presentation.ui.parts.WeatherList
import ru.itis.myapplication.features.weatherlist.presentation.ui.parts.WeatherListShimmer
import ru.itis.myapplication.features.weatherlist.presentation.ui.viewmodel.WeatherViewModel

@Composable
fun MainScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    onItemClick: (Weather) -> Unit
) {
    val state = viewModel.viewStates.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadWeather()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.value.isLoading -> WeatherListShimmer()
            state.value.weatherList.isNotEmpty() -> WeatherList(
                items = state.value.weatherList,
                onItemClick = onItemClick
            )
        }

        IconButton(
            onClick = { viewModel.showAddCityDialog()},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.default_description)
            )
        }

        if (state.value.showAddCityDialog) {
            AddCityDialog(
                onAddCity = {
                    viewModel.addCity(it)
                },
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
