package ru.ucheba.hw1.ui.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.ucheba.hw1.databinding.ItemListBinding
import ru.ucheba.hw1.model.MultipleHoldersData
import ru.ucheba.hw1.model.SecondHolderData

class ImageViewHolder(
    val viewBinding: ItemListBinding,
    private val requestManager: RequestManager,
    private val action: (SecondHolderData) -> Unit,
    private var onLongClick: (MultipleHoldersData) -> Unit,
) : RecyclerView.ViewHolder(viewBinding.root) {



    fun bindItem(itemData: SecondHolderData) {
        with(viewBinding) {
            requestManager.load(itemData.imageUrl)
                .into(pictureIv)

            descTv.text = itemData.headerText
            root.setOnClickListener {
                action.invoke(itemData)
            }
            root.setOnLongClickListener {
                onLongClick.invoke(itemData)
                true
            }
        }
    }

}