package ru.itis.presentation.ui.parts

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun PieChartCompose(
    modifier: Modifier = Modifier,
    sectors: List<Pair<Int, Int>>,
    colors: List<Int> = emptyList()
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            PieChartView(context).apply {
                setData(sectors, colors)
            }
        },
        update = { view ->
            view.setData(sectors, colors)
        }
    )
}
