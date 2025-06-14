package ru.itis.graphic.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import ru.itis.graphic.presentation.parts.BackButtonTopBar
import ru.itis.presentation.ui.parts.PieChartCompose
import ru.itis.graphic.utils.Constants
import kotlin.collections.map

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomViewScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            BackButtonTopBar(onBack)
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.TopCenter

            ) {
                PieChartCompose(
                    modifier = Modifier
                        .width(320.dp)
                        .height(320.dp),
                    sectors = Constants.sectors,
                    colors = Constants.colors.map {
                        ContextCompat.getColor(LocalContext.current, it)
                    }
                )

            }
        }
    )
}
