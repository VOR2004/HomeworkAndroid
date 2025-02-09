package ru.ucheba.hw1.screens.compose

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.ucheba.hw1.R
import ru.ucheba.hw1.viewmodel.AppViewModel
import ru.ucheba.hw1.screens.compose.ui.text.TextFieldEmail
import ru.ucheba.hw1.screens.compose.ui.text.TextFieldNickname
import ru.ucheba.hw1.screens.compose.ui.text.TextFieldPassword
import ru.ucheba.hw1.utils.AuthUtils
import java.util.UUID

@Composable
fun RegistrationScreen(
    onNavigateToSignInScreen: () -> Unit,
    onNavigateToMainScreen: () -> Unit,
    vm: AppViewModel
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isEmailError by remember { mutableStateOf(false) }
    var isNicknameError by remember { mutableStateOf(false) }
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
                onClick = { onNavigateToSignInScreen() },
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = stringResource(R.string.or_sign_in),
                    modifier = Modifier.padding(end = 8.dp))
                Icon(
                    painterResource(R.drawable.baseline_how_to_reg_24),
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
            TextFieldEmail(
                email = email,
                onEmailChange = {
                    email = it
                    isEmailError = !AuthUtils.checkEmail(it) },
                isError = isEmailError
            )
            TextFieldNickname(
                nickname = nickname,
                onNicknameChange = {
                    nickname = it
                    isNicknameError = !AuthUtils.checkNickname(it) },
                isError = isNicknameError
            )
            TextFieldPassword(
                password = password,
                onPasswordChange = {
                password = it
                isPasswordError = !AuthUtils.checkPassword(it) },
                isError = isPasswordError
            )
            Button(
                onClick = {
                    isEmailError = !AuthUtils.checkEmail(email)
                    isNicknameError = !AuthUtils.checkNickname(nickname)
                    isPasswordError = !AuthUtils.checkPassword(password)

                    isFormValid = !isEmailError && !isNicknameError && !isPasswordError
                    scope.launch {
                        if (isFormValid) {
                            if (!vm.checkEmail(email)) {
                                val id = UUID.randomUUID().toString()
                                vm.saveUser(id = id, userName = nickname, email = email, password = password)
                                vm.saveLoginSession(id)
                                onNavigateToMainScreen()
                            } else {
                                Toast.makeText(context, R.string.email_taken, Toast.LENGTH_SHORT).show()
                                isEmailError = true
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 32.dp, end = 32.dp)
            ) {
                Text(text = stringResource(R.string.reg))
            }
        }
    }
}
