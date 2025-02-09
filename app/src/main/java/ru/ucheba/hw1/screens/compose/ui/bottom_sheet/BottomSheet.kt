package ru.ucheba.hw1.screens.compose.ui.bottom_sheet

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R
import ru.ucheba.hw1.viewmodel.AppViewModel
import ru.ucheba.hw1.utils.rememberPermissionHandler
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieBottomSheet(
    onDismissRequest: () -> Unit,
    vm: AppViewModel
) {
    val sheetState = rememberModalBottomSheetState()
    var movieName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var criticRating by remember { mutableIntStateOf(0) }
    var pictureUri by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    val pickMediaLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                pictureUri = uri.toString()
            }
        }

    val pickMedia = {
        pickMediaLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        AskForPermission()
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(end = 16.dp, start = 16.dp)
        ) {
            TextField(
                value = movieName,
                onValueChange = { movieName = it },
                label = { Text(stringResource(R.string.name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(48.dp)
            )

            TextField(
                value = year,
                onValueChange = { newValue ->
                    val filteredValue = newValue.filter {
                        it.isDigit()
                    }
                    year = filteredValue },
                label = { Text(stringResource(R.string.year)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(48.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(96.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                for (i in 1..5) {
                    val isFilled = i <= criticRating
                    Icon(
                        imageVector = if (isFilled) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = stringResource(R.string.description_star),
                        modifier = Modifier.clickable { criticRating = i },
                        tint = if (isFilled) {
                            colorResource(R.color.yellow_default)
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
                Text(
                    text = stringResource(R.string.review),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (movieName.isNotEmpty() && year.isNotEmpty()) {
                            val userId = vm.getId()
                            vm.addMovie(
                                id = UUID.randomUUID().toString(),
                                userId = userId!!,
                                movieName = movieName,
                                description = description,
                                year = year,
                                rating = criticRating.toFloat(),
                                uri = pictureUri
                            )
                            vm.getMoviesForUser(userId)
                            onDismissRequest()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Text(stringResource(R.string.save))
                }

                TextButton(
                    onClick = {
                        pickMedia()
                    },
                    modifier = Modifier
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_attachment_24),
                        contentDescription = stringResource(R.string.more_options),
                    )
                }
            }
        }
    }
}

@Composable
fun AskForPermission() {
    val context = LocalContext.current
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionHandler = rememberPermissionHandler()
        permissionHandler.InitializePermissions()

        LaunchedEffect(Unit) {
            val notificationsPermission = context.checkSelfPermission(
                Manifest.permission.READ_MEDIA_IMAGES
            )
            if (
                notificationsPermission != PackageManager.PERMISSION_GRANTED
            )
            {
                permissionHandler.requestSinglePermission (
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            }
        }
    }
}