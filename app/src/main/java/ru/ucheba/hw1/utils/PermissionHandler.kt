package ru.ucheba.hw1.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

private const val PREF_KEY_PERMISSION_RATIONALE = "permission_rationale_"

@Composable
fun rememberPermissionHandler(
    onSinglePermissionGranted: (() -> Unit)? = null,
    onSinglePermissionDenied: (() -> Unit)? = null,
    onSinglePermissionPermanentlyDenied: (() -> Unit)? = null
) : PermissionHandler {
    val context = LocalContext.current
    return remember {
        PermissionHandler(
            context = context,
            onSinglePermissionGranted = onSinglePermissionGranted,
            onSinglePermissionDenied = onSinglePermissionDenied,
            onSinglePermissionPermanentlyDenied = onSinglePermissionPermanentlyDenied
        )
    }
}

class PermissionHandler(
    private val context: Context,
    private val onSinglePermissionGranted: (() -> Unit)? = null,
    private val onSinglePermissionDenied: (() -> Unit)? = null,
    private val onSinglePermissionPermanentlyDenied: (() -> Unit)? = null
) {

    private var singlePermissionResult: ActivityResultLauncher<String>? by mutableStateOf(null)
    private var permissionState by mutableStateOf(PermissionState())

    private val prefs: SharedPreferences =
        context.getSharedPreferences("permission_prefs", Context.MODE_PRIVATE)

    @Composable
    fun InitializePermissions() {
        singlePermissionResult = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            handlePermissionResult(isGranted)
        }
    }

    private fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            permissionState = permissionState.copy(singlePermissionGranted = true)
            onSinglePermissionGranted?.invoke()
            return
        }

        val permission = permissionState.currentPermission ?: return

        val shouldShowRationale = shouldShowRequestPermissionRationale(permission)

        val storedRationale = prefs.getBoolean(PREF_KEY_PERMISSION_RATIONALE + permission, false)

        if (storedRationale && !shouldShowRationale) {
            permissionState = permissionState.copy(isPermanentlyDenied = true)
            onSinglePermissionPermanentlyDenied?.invoke()
        } else {
            permissionState = permissionState.copy(isPermanentlyDenied = false)
            onSinglePermissionDenied?.invoke()
            updateRationalePreference(permission, shouldShowRationale)
        }
        permissionState = permissionState.copy(singlePermissionGranted = false)

    }

    private fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return (context as? androidx.activity.ComponentActivity)?.shouldShowRequestPermissionRationale(permission)
            ?: false
    }

    private fun updateRationalePreference(permission: String, shouldShowRationale: Boolean) {
        val storedRationale = prefs.getBoolean(PREF_KEY_PERMISSION_RATIONALE + permission, false)

        if(!storedRationale && shouldShowRationale) {
            prefs.edit().putBoolean(PREF_KEY_PERMISSION_RATIONALE + permission, shouldShowRationale).apply()
        }
    }

    fun requestSinglePermission(permission: String) {
        permissionState = permissionState.copy(currentPermission = permission)
        singlePermissionResult?.launch(permission)
    }

    data class PermissionState (
        val singlePermissionGranted: Boolean? = null,
        val isPermanentlyDenied: Boolean = false,
        val currentPermission: String? = null
    )
}