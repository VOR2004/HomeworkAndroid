package ru.ucheba.hw1.utils

import android.content.Context
import android.util.TypedValue

fun getValueInDp(value: Float, ctx: Context): Float {
    val metrics = ctx.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, metrics)
}