package ru.ucheba.hw1.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ucheba.hw1.databinding.ButtonItemBinding
import ru.ucheba.hw1.model.QuestionsData
import ru.ucheba.hw1.ui.viewholder.BtnViewHolder

class AdapterRV(
    var question: QuestionsData,
    private val onCheck: (position: Int) -> Unit,
) : RecyclerView.Adapter<BtnViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BtnViewHolder {
        return BtnViewHolder(
            ButtonItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCheck = onCheck
        )
    }

    override fun onBindViewHolder(holder: BtnViewHolder, position: Int) {
        (holder as? BtnViewHolder)?.bindItem(question.answers[position])
        (holder as? BtnViewHolder)?.bindCheck(question.selected == position)
    }

    override fun onBindViewHolder(
        holder: BtnViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        }

        else {
            when (payloads.first()) {
                is Boolean -> {
                    holder.bindCheck(payloads.first() as Boolean)
                }

                else -> {
                    super.onBindViewHolder(holder, position, payloads)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return question.answers.size
    }


}