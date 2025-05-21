package ru.itis.graphic.presentation.parts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.itis.graphic.R

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BackButtonTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { stringResource(R.string.title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back))
            }
        }
    )
}
