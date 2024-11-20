package ru.ucheba.hw1.ui.viewholder

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.ucheba.hw1.databinding.ItemHolderButtonsBinding
import ru.ucheba.hw1.model.FirstHolderData

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