package ru.ucheba.hw1.screens.compose.ui.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R

@Composable
fun ErrorTextEmail(isError: Boolean, errorMessage: String) {
    val errorTextHeight = 24.dp
    Box(modifier = Modifier.height(errorTextHeight)) {
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            )
        }
    }
}

@Composable
fun TextFieldEmail(email: String, onEmailChange: (String) -> Unit, isError: Boolean) {
    Column {
        TextField(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            label = { Text(text = stringResource(id = R.string.enter_email)) },
            isError = isError,
            singleLine = true,
        )

        ErrorTextEmail(
            isError = isError,
            errorMessage = stringResource(id = R.string.invalid_email)
        )
    }
}