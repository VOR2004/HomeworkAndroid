package ru.ucheba.hw1.utils

import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class PermissionHandler(
    private val onSinglePermissionGranted: (() -> Unit)? = null,
    private val onSinglePermissionDenied: (() -> Unit)? = null,
    private val onMultiplePermissionsGranted: (() -> Unit)? = null,
    private val onMultiplePermissionsDenied: (() -> Unit)? = null
) {

    private var activity: AppCompatActivity? = null

    private var singlePermissionResult: ActivityResultLauncher<String>? = null

    private var multiplePermissionResult: ActivityResultLauncher<Array<String>>? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun initContracts(activity: AppCompatActivity) {
        if (this.activity == null) {
            this.activity = activity
        }

        if (singlePermissionResult == null) {
            singlePermissionResult = this.activity?.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    onSinglePermissionGranted?.invoke()
                } else {
                    onSinglePermissionDenied?.invoke()
                }
            }
        }
        if (multiplePermissionResult == null) {
            multiplePermissionResult =
                this.activity?.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsResultMap ->

                    val allPermissionsGranted = permissionsResultMap.all { it.value }
                    if (allPermissionsGranted) {
                        onMultiplePermissionsGranted?.invoke()
                    } else {
                        onMultiplePermissionsDenied?.invoke()
                    }
                }
        }
    }

    fun requestSinglePermission(permission: String) {
        singlePermissionResult?.launch(permission)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestMultiplePermissions(permission: List<String>) {
        multiplePermissionResult?.launch(permission.toTypedArray())
    }
}