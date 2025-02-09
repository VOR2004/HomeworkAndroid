package ru.ucheba.hw1.screens.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.ucheba.hw1.R
import ru.ucheba.hw1.viewmodel.AppViewModel
import ru.ucheba.hw1.screens.compose.ui.text.TextFieldPassword
import ru.ucheba.hw1.screens.compose.ui.text.TextFieldUsernameOrEmail
import ru.ucheba.hw1.utils.AuthUtils

@Composable
fun SignInScreen(
    onNavigateToSignUpScreen: () -> Unit,
    onNavigateToMainScreen: () -> Unit,
    vm: AppViewModel
    ) {
    val scope = rememberCoroutineScope()

    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isUsernameOrEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    var isFormValid by remember { mutableStateOf(false) }

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onNavigateToSignUpScreen() },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = stringResource(R.string.or_sign_up),
                    modifier = Modifier.padding(end = 8.dp))
                Icon(
                    painterResource(R.drawable.baseline_app_registration_24),
                    contentDescription = stringResource(R.string.more_options)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldUsernameOrEmail(
                usernameOrEmail = usernameOrEmail,
                onUsernameOrEmailChange = {
                    usernameOrEmail = it
                    isUsernameOrEmailError = !AuthUtils.isValidUsernameOrEmail(it)
                },
                isError = isUsernameOrEmailError
            )

            TextFieldPassword(
                password = password,
                onPasswordChange = {
                    password = it
                    isPasswordError = !AuthUtils.checkPassword(it)
                },
                isError = isPasswordError
            )

            Button(
                onClick = {
                    isUsernameOrEmailError = !AuthUtils.isValidUsernameOrEmail(usernameOrEmail)
                    isPasswordError = !AuthUtils.checkPassword(password)

                    isFormValid = !isUsernameOrEmailError && !isPasswordError
                    scope.launch {
                        if (isFormValid) {
                            val isEmail = AuthUtils.checkIfEmail(usernameOrEmail)
                            if (
                                AuthUtils.verifyPasswordPBKDF2(
                                    password,
                                    vm.getHashPassword(isEmail, usernameOrEmail))
                                ) {
                                vm.saveLoginSession(vm.getId(isEmail, usernameOrEmail))
                                onNavigateToMainScreen()
                            }
                            else {
                                isUsernameOrEmailError = true
                                isPasswordError = true
                                password = ""
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(text = stringResource(R.string.sign_in))
            }
        }
    }
}