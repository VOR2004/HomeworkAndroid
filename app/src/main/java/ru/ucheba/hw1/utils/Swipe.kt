package ru.ucheba.hw1.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.ucheba.hw1.adapter.recycler.AdapterWithMultipleHolders
import ru.ucheba.hw1.model.MultipleHoldersData
import ru.ucheba.hw1.ui.viewholder.ButtonViewHolder

class Swipe {
    fun swipeLeft(adapter: AdapterWithMultipleHolders, list: MutableList<MultipleHoldersData>): ItemTouchHelper {
        val swipe = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return 0.66f
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                if (viewHolder is ButtonViewHolder) return 0
                return super.getSwipeDirs(recyclerView, viewHolder)
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (position != 0) {
                    list.removeAt((position))
                }
                adapter.updateData(list)
            }
        }
        )
        return swipe
    }
}