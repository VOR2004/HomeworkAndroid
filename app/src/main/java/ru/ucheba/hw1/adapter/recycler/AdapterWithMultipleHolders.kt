package ru.ucheba.hw1.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.ucheba.hw1.R
import ru.ucheba.hw1.databinding.ItemHolderButtonsBinding
import ru.ucheba.hw1.databinding.ItemListBinding
import ru.ucheba.hw1.model.FirstHolderData
import ru.ucheba.hw1.model.MultipleHoldersData
import ru.ucheba.hw1.model.SecondHolderData
import ru.ucheba.hw1.ui.viewholder.ButtonViewHolder
import ru.ucheba.hw1.ui.viewholder.ImageViewHolder
import ru.ucheba.hw1.utils.SampleDiffUtil

class AdapterWithMultipleHolders(
    private val requestManager: RequestManager,
    items: List<MultipleHoldersData>,
    private val onButtonClickLinear: () -> Unit,
    private val onButtonClickGrid: () -> Unit,
    private val action: (SecondHolderData) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<MultipleHoldersData>()

    init {
        dataList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_holder_buttons -> {
                ButtonViewHolder(
                    onClickActionLinear = onButtonClickLinear,
                    onClickActionGrid = onButtonClickGrid,
                    viewBinding = ItemHolderButtonsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            R.layout.item_list -> {
                ImageViewHolder(
                    viewBinding = ItemListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    requestManager = requestManager,
                    action = action
                )
            }

            else -> throw IllegalStateException("Unknown holder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dataList[position]) {

            is SecondHolderData -> {
                (holder as? ImageViewHolder)?.bindItem(itemData = dataList[position] as SecondHolderData)
            }

            else -> Unit
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        return when (item) {
            is FirstHolderData -> {
                R.layout.item_holder_buttons
            }

            is SecondHolderData -> {
                R.layout.item_list
            }
        }
    }

    fun updateData(newList: List<MultipleHoldersData>) {
        val diffCallback = SampleDiffUtil(
            oldList = dataList,
            newList = newList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        dataList.clear()
        dataList.addAll(newList)
    }
}