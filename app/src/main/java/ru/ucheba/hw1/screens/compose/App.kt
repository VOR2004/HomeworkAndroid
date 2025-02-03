package ru.ucheba.hw1.screens.compose

import CoroutineManager
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import ru.ucheba.hw1.screens.compose.ui.bottom_sheet.Bs
import ru.ucheba.hw1.utils.rememberPermissionHandler
import ru.ucheba.hw1.screens.compose.ui.buttons.ButtonCancel
import ru.ucheba.hw1.screens.compose.ui.buttons.ButtonStart
import ru.ucheba.hw1.screens.compose.ui.dropdown.LongBasicDropdownMenu
import ru.ucheba.hw1.screens.compose.ui.radiogroup.RadioButtonSingleSelection
import ru.ucheba.hw1.screens.compose.ui.radiogroup.RadioButtonSingleSelection2
import ru.ucheba.hw1.screens.compose.ui.text.CoroutinesTextField
import ru.ucheba.hw1.utils.CoroutineCancellationMode

@Composable
fun App() {
    val context = LocalContext.current
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    val coroutineManager = CoroutineManager(context)

    LifecycleResumeEffect(Unit) {
        onPauseOrDispose {
            if (
                coroutineManager.getCancelMode() ==
                CoroutineCancellationMode.CANCEL_ON_BACKGROUND
                )
            {
                coroutineManager.onAppBackground()
            }
        }
    }

    AskForPermission(
        onPermissionPermanentlyDenied = {
            showBottomSheet.value = true
        }
    )
    Surface {
        Scaffold(
            bottomBar = {
                Column {
                    ButtonStart(coroutineManager)
                    ButtonCancel(coroutineManager)
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                CoroutinesTextField(coroutineManager)
                RadioButtonSingleSelection(modifier = Modifier, coroutineManager)
                RadioButtonSingleSelection2(modifier = Modifier, coroutineManager)
                LongBasicDropdownMenu(coroutineManager)
                Bs(
                    showBottomSheet.value,
                    onDismissRequest = {
                        showBottomSheet.value = false
                    }
                )
            }
        }
    }
}

@Composable
private fun AskForPermission(onPermissionPermanentlyDenied: () -> Unit) {
    val context = LocalContext.current
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionHandler = rememberPermissionHandler(
            onSinglePermissionPermanentlyDenied = {
                onPermissionPermanentlyDenied()
            }
        )
        permissionHandler.InitializePermissions()

        LaunchedEffect(Unit) {
            val notificationsPermission = context.checkSelfPermission(
                Manifest.permission.POST_NOTIFICATIONS
            )
            if (
                notificationsPermission != PackageManager.PERMISSION_GRANTED
                )
            {
                permissionHandler.requestSinglePermission (
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }
}
