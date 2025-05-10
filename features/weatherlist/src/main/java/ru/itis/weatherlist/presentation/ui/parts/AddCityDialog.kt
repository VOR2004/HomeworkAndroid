package ru.itis.weatherlist.presentation.ui.parts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.itis.weatherlist.R

@Composable
fun AddCityDialog(
    onAddCity: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var cityName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Column {
                TextField(
                    value = cityName,
                    onValueChange = { cityName = it },
                    label = { stringResource(R.string.city_name) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddCity(cityName)
                    onDismiss()
                },
                enabled = cityName.isNotBlank()
            ) {
                Text(stringResource(R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}
