package ru.ucheba.hw1.screens.compose.ui.text

import CoroutineManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R

@Composable
fun CoroutinesTextField(cm: CoroutineManager) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { it.isDigit() }
            text = filteredValue
            if (text.isNotBlank()) {
                cm.setNumberOfCoroutines(text.toInt())
            }
        },
        label = { Text(text = stringResource(R.string.number_of_coroutines)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp),
        isError = cm.getNumberOfCoroutines() == 0 && cm.ifError(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

