package ru.itis.graphic.presentation.parts

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlinx.collections.immutable.ImmutableList
import kotlin.math.max

@Composable
fun Graphics(
    points: ImmutableList<Float>,
    modifier: Modifier = Modifier
) {
    val lineColor = MaterialTheme.colorScheme.primary

    val areaBrush = Brush.verticalGradient(
        0f to lineColor.copy(alpha = .3f),
        1f to Color.Transparent
    )

    Canvas(modifier = modifier) {
        val stepX = size.width / max(1, points.size - 1)
        val maxY = (points.maxOrNull() ?: 0f).takeIf { it > 0 } ?: 1f
        val scaleY = size.height / maxY

        val gridLineColor = Color.Gray
        val horizontalLines = 5
        val verticalLines = points.size

        repeat(horizontalLines + 1) { i ->
            val y = i * size.height / horizontalLines
            drawLine(
                color = gridLineColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1f
            )
        }

        repeat(verticalLines) { i ->
            val x = i.toFloat() * stepX
            drawLine(
                color = gridLineColor,
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = 1f
            )
        }

        drawLine(
            Color.LightGray,
            Offset(0f, size.height),
            Offset(size.width, size.height),
            strokeWidth = 2f
        )
        drawLine(
            Color.LightGray,
            Offset(0f, 0f),
            Offset(0f, size.height),
            strokeWidth = 2f
        )

        val linePath = Path()

        val fillPath = Path()

        points.forEachIndexed { index, value ->
            val x = index * stepX
            val y = size.height - value * scaleY

            if (index == 0) {
                linePath.moveTo(x, y)
                fillPath.moveTo(x, size.height)
                fillPath.lineTo(x, y)
            } else {
                linePath.lineTo(x, y)
                fillPath.lineTo(x, y)
            }
        }

        fillPath.lineTo((points.lastIndex) * stepX, size.height)
        fillPath.close()

        drawPath(
            path = fillPath,
            brush = areaBrush
        )

        drawPath(
            path = linePath,
            color = lineColor,
            style = Stroke(width = 4f, cap = StrokeCap.Round)
        )

        points.forEachIndexed { i, v ->
            val x = i * stepX
            val y = size.height - v * scaleY
            drawCircle(lineColor, radius = 16f, center = Offset(x, y))
            drawCircle(Color.White, radius = 8f, center = Offset(x, y))
        }
    }
}

