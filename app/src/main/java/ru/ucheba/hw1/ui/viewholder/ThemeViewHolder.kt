package ru.ucheba.hw1.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.ucheba.hw1.databinding.ThemeItemBinding
import ru.ucheba.hw1.model.ThemeData

class ThemeViewHolder(
    private val viewBinding: ThemeItemBinding,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(viewBinding.root) {

    init {
        viewBinding.viewTheme.setOnClickListener {
            onClick(adapterPosition)
        }
    }

    fun bindItem(themeData: ThemeData) {
        viewBinding.viewTheme.setBackgroundResource(themeData.getColor())
    }
}