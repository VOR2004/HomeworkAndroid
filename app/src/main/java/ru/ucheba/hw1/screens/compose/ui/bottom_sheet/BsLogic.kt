package ru.ucheba.hw1.screens.compose.ui.bottom_sheet

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.ucheba.hw1.R
import ru.ucheba.hw1.key.KeyNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bs(showBottomSheet: Boolean, onDismissRequest: () -> Unit) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() }
            },
            sheetState = sheetState
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                            start = 24.dp,
                            end = 16.dp,
                            bottom = 24.dp
                        ),
                    text = stringResource(R.string.on_offed_notifications),
                    fontSize = 16f.sp,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() }
                        openNotificationSettings(context = context)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 32.dp
                        )
                ) {
                    Text(text = stringResource(R.string.goto_settings))
                }
            }
        }
    }
}

private fun openNotificationSettings(context: android.content.Context) {
    val intent = Intent().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        } else {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            addCategory(Intent.CATEGORY_DEFAULT)
                data = Uri.parse(KeyNames.PACKAGE_NAME + context.packageName)
        }
    }
    context.startActivity(intent)
}