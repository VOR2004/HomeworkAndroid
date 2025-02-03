package ru.ucheba.hw1.screens.compose.ui.buttons

import CoroutineManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R

@Composable
fun ButtonStart(cm: CoroutineManager) {
    Button(
        onClick = {
            cm.startCoroutines()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        Text (
            text = stringResource(R.string.begin_work)
        )
    }
}

@Composable
fun ButtonCancel(cm: CoroutineManager) {
    Button(
        onClick = {
            cm.cancelAllCoroutines()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, bottom = 32.dp)
    ) {
        Text (
            text = stringResource(R.string.discard_work)
        )
    }
}