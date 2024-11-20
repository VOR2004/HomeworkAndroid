package ru.ucheba.hw1.utils

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class LayoutChanger {

    fun changeLayoutRvToGrid(context: Context): LayoutManager {
        val grid = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false).apply {
            spanSizeLookup = object : SpanSizeLookup()  {
                override fun getSpanSize(position: Int): Int {
                    if (position == 0) {
                        return 3
                    }
                    else {
                        return 1
                    }
                }

            }
        }
        return grid
    }

    fun changeLayoutRvToLinear(context: Context): LayoutManager {
        val linear = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false)
        return linear
    }
}