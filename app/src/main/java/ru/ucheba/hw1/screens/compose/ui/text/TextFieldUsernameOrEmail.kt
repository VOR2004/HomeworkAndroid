package ru.ucheba.hw1.screens.compose.ui.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R

@Composable
fun TextFieldUsernameOrEmail(
    usernameOrEmail: String,
    onUsernameOrEmailChange: (String) -> Unit,
    isError: Boolean
) {
    Column {
        androidx.compose.material3.TextField(
            value = usernameOrEmail,
            onValueChange = onUsernameOrEmailChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            label = { Text(text = stringResource(R.string.user_or_email)) },
            isError = isError,
            singleLine = true,
        )

        ErrorTextUsernameOrEmail(
            isError = isError,
            errorMessage = stringResource(R.string.incorrect_user_or_email)
        )
    }
}

@Composable
fun ErrorTextUsernameOrEmail(isError: Boolean, errorMessage: String) {
    val errorTextHeight = 24.dp
    androidx.compose.foundation.layout.Box(modifier = Modifier.height(errorTextHeight)) {
        if (isError) {
            Text(
                text = errorMessage,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            )
        }
    }
}