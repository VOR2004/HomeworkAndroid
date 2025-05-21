package ru.itis.graphic.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.graphic.presentation.viewmodel.GraphViewModel
import ru.itis.graphic.presentation.parts.Graphics
import ru.itis.graphic.presentation.parts.DotsCountField
import ru.itis.graphic.presentation.parts.BackButtonTopBar
import ru.itis.graphic.presentation.parts.ValuesField
import ru.itis.presentation.ui.parts.ErrorBottomSheet
import ru.itis.graphic.R

@Composable
fun GraphScreen(
    onBack: () -> Unit,
    viewModel: GraphViewModel = hiltViewModel()
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { BackButtonTopBar(onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DotsCountField(
                value = state.pointsCountInput,
                onValueChange = viewModel::onCountChanged
            )

            Spacer(Modifier.height(16.dp))

            ValuesField(
                value = state.valuesInput,
                onValueChange = viewModel::onValuesChanged
            )

            Spacer(Modifier.height(16.dp))

            Button(onClick = viewModel::buildGraph) {
                Text(stringResource(R.string.build))
            }

            Spacer(Modifier.height(24.dp))

            if (state.points.isNotEmpty()) {
                Graphics(
                    points = state.points,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }
        }

        state.error?.let {
            ErrorBottomSheet(
                message = it,
                onDismiss = viewModel::dismissError
            )
        }
    }
}
