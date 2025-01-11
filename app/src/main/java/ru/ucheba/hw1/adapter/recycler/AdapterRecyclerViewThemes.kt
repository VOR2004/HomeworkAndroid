package ru.ucheba.hw1.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ucheba.hw1.databinding.ThemeItemBinding
import ru.ucheba.hw1.model.ThemeData

import ru.ucheba.hw1.ui.viewholder.ThemeViewHolder

class AdapterRecyclerViewThemes(
    private val itemsTheme: List<ThemeData>,
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<ThemeViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
        return ThemeViewHolder(
            ThemeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bindItem(itemsTheme[position])
    }

    override fun getItemCount(): Int {
        return itemsTheme.size
    }
}


