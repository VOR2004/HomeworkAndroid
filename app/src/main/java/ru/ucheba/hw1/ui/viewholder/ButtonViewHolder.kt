package ru.ucheba.hw1.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.ucheba.hw1.databinding.ItemHolderButtonsBinding

class ButtonViewHolder(
    viewBinding: ItemHolderButtonsBinding,
    private val onClickActionLinear: () -> Unit,
    private val onClickActionGrid: () -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

    init {
        viewBinding.buttonRv1.setOnClickListener {
            onClickActionLinear()
        }
        viewBinding.buttonRv2.setOnClickListener {
            onClickActionGrid()
        }
    }
}