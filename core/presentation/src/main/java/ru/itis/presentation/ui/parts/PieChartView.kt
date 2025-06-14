package ru.itis.presentation.ui.parts

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.*
import androidx.core.graphics.toColorInt

class PieChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val minPercentToShowLabel = 5
    private val gapAngle = 3f

    private var thicknessRatio = 0.24f
    private var textRatio = 0.04f
    private var offsetRatio = 0.08f

    private val highlightStrokeFactor = 1.20f
    private val highlightShadowRadius = 15f

    private var sectors: List<Pair<Int, Int>> = emptyList()
    private var colors: List<Int> = basePalette
    private var selectedIndex = -1

    private val sectorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.BUTT
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        typeface = Typeface.DEFAULT_BOLD
        textAlign = Paint.Align.CENTER
    }

    private val baseRect = RectF()
    private val shiftedRect = RectF()
    private var donutThicknessPx = 0f
    private var offsetRadiusPx = 0f

    fun setData(
        sectors: List<Pair<Int, Int>>,
        inputColors: List<Int>,
    ) {
        require(sectors.sumOf { it.second } == 100) { ERROR_MESSAGE }

        this.sectors = sectors
        val count = sectors.size

        val minColorsNeeded = when {
            count <= inputColors.size -> count
            count % 2 == 0 -> max(2, inputColors.size)
            else -> max(3, inputColors.size)
        }

        val palette = buildList {
            addAll(inputColors.distinct())
            var idx = 0
            while (size < minColorsNeeded) {
                val c = basePalette[idx++ % basePalette.size]
                if (c !in this) add(c)
            }
        }

        colors = ensureNoAdjacentDuplicates(List(count) { palette[it % palette.size] })
        selectedIndex = -1
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultSize = (200 * resources.displayMetrics.density).toInt()
        val w = resolveSize(defaultSize, widthMeasureSpec)
        val h = resolveSize(defaultSize, heightMeasureSpec)
        val size = min(w, h)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        if (sectors.isEmpty()) return

        val contentW = width - paddingLeft - paddingRight
        val contentH = height - paddingTop - paddingBottom
        val diameter = min(contentW, contentH).toFloat()

        donutThicknessPx = diameter * thicknessRatio
        offsetRadiusPx = diameter * offsetRatio
        textPaint.textSize = diameter * textRatio

        val halfStrokeBase = donutThicknessPx / 2
        val extraStrokeHalf = donutThicknessPx * (highlightStrokeFactor - 1f) / 2f
        val insetExtra = extraStrokeHalf + highlightShadowRadius
        val insetTotal = offsetRadiusPx + halfStrokeBase + insetExtra

        baseRect.set(
            paddingLeft + insetTotal,
            paddingTop + insetTotal,
            paddingLeft + diameter - insetTotal,
            paddingTop + diameter - insetTotal
        )

        var startAngle = -90f
        sectors.forEachIndexed { i, (_, percent) ->
            val sweepAngle = percent * 3.6f - gapAngle
            val midAngle = startAngle + sweepAngle / 2 + gapAngle / 2

            val dx = offsetRadiusPx * cos(Math.toRadians(midAngle.toDouble())).toFloat()
            val dy = offsetRadiusPx * sin(Math.toRadians(midAngle.toDouble())).toFloat()
            shiftedRect.set(baseRect)
            shiftedRect.offset(dx, dy)

            if (i == selectedIndex) {
                sectorPaint.strokeWidth = donutThicknessPx * highlightStrokeFactor
                sectorPaint.setShadowLayer(highlightShadowRadius, 0f, 0f, Color.BLACK)
            } else {
                sectorPaint.strokeWidth = donutThicknessPx
                sectorPaint.clearShadowLayer()
            }
            sectorPaint.color = colors[i]

            canvas.drawArc(
                shiftedRect,
                startAngle + gapAngle / 2,
                sweepAngle,
                false,
                sectorPaint
            )

            if (percent >= minPercentToShowLabel)
                drawPercent(canvas, shiftedRect, midAngle, percent)

            startAngle += percent * 3.6f
        }
    }

    private fun drawPercent(
        canvas: Canvas,
        rect: RectF,
        angle: Float,
        percent: Int
    ) {
        val r = rect.width() / 2
        val cx = rect.centerX()
        val cy = rect.centerY()
        val rad = Math.toRadians(angle.toDouble())

        val x = (cx + r * cos(rad)).toFloat()
        val y = (cy + r * sin(rad)).toFloat() -
                (textPaint.descent() + textPaint.ascent()) / 2

        canvas.drawText("$percent%", x, y, textPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val idx = findSectorIndex(event.x, event.y)
                if (idx != selectedIndex) {
                    selectedIndex = idx
                    invalidate()
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                if (findSectorIndex(event.x, event.y) == -1 && selectedIndex != -1) {
                    selectedIndex = -1
                    invalidate()
                }
                performClick()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

    private fun ensureNoAdjacentDuplicates(src: List<Int>): List<Int> {
        if (src.isEmpty()) return src
        val res = src.toMutableList()
        for (i in 1 until res.size) {
            if (res[i] == res[i - 1]) {
                val alt = src.distinct().firstOrNull {
                    it != res[i - 1] && (i == res.lastIndex || it != res[i + 1])
                }
                if (alt != null) res[i] = alt
            }
        }
        return res
    }

    private fun findSectorIndex(x: Float, y: Float): Int {
        val cx = width / 2f
        val cy = height / 2f
        val dx = x - cx
        val dy = y - cy
        val dist = hypot(dx, dy)

        val outerR = width / 2f
        val innerR = outerR - donutThicknessPx
        if (dist !in innerR..outerR) return -1

        val angle = (Math.toDegrees(atan2(dy.toDouble(), dx.toDouble())) + 450) % 360
        var acc = 0f
        sectors.forEachIndexed { idx, p ->
            val sweep = p.second * 3.6f
            if (angle in acc..acc + sweep) return idx
            acc += sweep
        }
        return -1
    }

    companion object {
        val basePalette = listOf(
            "#4F81BD".toColorInt(),
            "#C0504D".toColorInt(),
            "#B7B7B7".toColorInt(),
        )
        const val ERROR_MESSAGE = "Сумма процентов должна быть 100"
    }
}
