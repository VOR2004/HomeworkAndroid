package ru.ucheba.hw1.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import ru.ucheba.hw1.databinding.ButtonItemBinding

class BtnViewHolder(
    private val viewBinding: ButtonItemBinding,
    private val onCheck: (Int) -> Unit,
) : RecyclerView.ViewHolder(viewBinding.root) {

    init {
        viewBinding.radiobtn.setOnCheckedChangeListener { _,_ ->
            onCheck(adapterPosition)
        }
    }

    fun bindItem(answer: String) {
        viewBinding.radiobtn.text = answer
    }

    fun bindCheck(checked: Boolean) {
        viewBinding.radiobtn.isChecked = checked
    }

}