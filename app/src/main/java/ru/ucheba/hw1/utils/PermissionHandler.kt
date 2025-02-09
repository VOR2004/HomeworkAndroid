package ru.ucheba.hw1.utils

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*

@Composable
fun rememberPermissionHandler(
    onSinglePermissionGranted: (() -> Unit)? = null,
    onSinglePermissionDenied: (() -> Unit)? = null,
) : PermissionHandler {
    return remember {
        PermissionHandler(onSinglePermissionGranted = onSinglePermissionGranted,
            onSinglePermissionDenied = onSinglePermissionDenied,
        )
    }
}

class PermissionHandler(
    private val onSinglePermissionGranted: (() -> Unit)? = null,
    private val onSinglePermissionDenied: (() -> Unit)? = null,
) {

    private lateinit var singlePermissionResult: ActivityResultLauncher<String>

    private var permissionState by mutableStateOf(PermissionState())

    @Composable
    fun InitializePermissions() {
        singlePermissionResult = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                permissionState = permissionState.copy(singlePermissionGranted = true)
                onSinglePermissionGranted?.invoke()

            } else {
                permissionState = permissionState.copy(singlePermissionGranted = false)
                onSinglePermissionDenied?.invoke()
            }

        }
    }

    fun requestSinglePermission(permission: String) {
        singlePermissionResult.launch(permission)
    }

    data class PermissionState (
        val singlePermissionGranted: Boolean? = null,
        val multiplePermissionsGranted: Boolean? = null
    )
}

