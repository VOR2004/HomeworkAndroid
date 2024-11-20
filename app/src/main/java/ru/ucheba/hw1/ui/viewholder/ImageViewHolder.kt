package ru.ucheba.hw1.ui.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.ucheba.hw1.databinding.ItemListBinding
import ru.ucheba.hw1.model.SecondHolderData

class ImageViewHolder(
    val viewBinding: ItemListBinding,
    private val requestManager: RequestManager,
    private val action: (SecondHolderData) -> Unit
) : RecyclerView.ViewHolder(viewBinding.root) {

//    init {
//        viewBinding.root.setOnClickListener {
//            action.invoke(adapterPosition)
//        }
//    }

    fun bindItem(itemData: SecondHolderData) {
        with(viewBinding) {
            requestManager.load(itemData.imageUrl)
                .into(pictureIv)

            descTv.text = itemData.headerText
            root.setOnClickListener {
                action.invoke(itemData)
            }
        }

       // viewBinding.divider.isVisible = adapterPosition != count - 1


        //viewBinding.root.setBackgroundColor(itemData.background)
    }

}