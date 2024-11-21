package ru.ucheba.hw1.screens.bottom_sheet

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.ucheba.hw1.R
import ru.ucheba.hw1.databinding.FragmentBottomSheetBinding
import ru.ucheba.hw1.model.MultipleHoldersData
import ru.ucheba.hw1.model.SecondHolderData
import ru.ucheba.hw1.multipleTypesList.MultipleTypesFragment
import ru.ucheba.hw1.repository.ScreensContentRepository
import ru.ucheba.hw1.utils.StringUtils

class BottomSheetFragment: BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private val viewBinding: FragmentBottomSheetBinding by viewBinding(FragmentBottomSheetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews((parentFragment as MultipleTypesFragment).list)
    }

    private fun initViews(list: MutableList<MultipleHoldersData>) {
        with(viewBinding) {
            val headers = ScreensContentRepository.headers
            val descs = ScreensContentRepository.desc
            val image = ScreensContentRepository.imageUrls

            buttonAddAt.setOnClickListener {
                val text = textInputEtBs.text.toString().toIntOrNull()

                if (text != null) {
                    repeat(text) {
                        list.add((1..list.size).random() , SecondHolderData(
                            id = StringUtils().getRandomString(100000),
                            headerText = headers[headers.indices.random()],
                            descText = descs[descs.indices.random()],
                            imageUrl = image[image.indices.random()],
                        ))
                        (parentFragment as MultipleTypesFragment).rvAdapter?.updateData(list)
                    }
                }
            }

            buttonDeleteAt.setOnClickListener {
                val text = textInputEtBs.text.toString().toIntOrNull()
                if (text != null) {
                    if (text <= list.size - 1) {
                        repeat(text) {
                            list.removeAt((1..<list.size).random())
                            (parentFragment as MultipleTypesFragment).rvAdapter?.updateData(list)
                        }
                    }
                    else {
                        repeat(list.size-1) {
                            list.removeAt((1..<list.size).random())
                            (parentFragment as MultipleTypesFragment).rvAdapter?.updateData(list)
                        }
                    }
                }
            }

            buttonAdd.setOnClickListener {
                list.add((1..list.size).random() , SecondHolderData(
                    id = StringUtils().getRandomString(100000),
                    headerText = headers[headers.indices.random()],
                    descText = descs[descs.indices.random()],
                    imageUrl = image[image.indices.random()],
                ))
                (parentFragment as MultipleTypesFragment).rvAdapter?.updateData(list)
            }

            buttonDelete.setOnClickListener{
                if (list.size != 1) {
                    list.removeAt((1..<list.size).random())
                    (parentFragment as MultipleTypesFragment).rvAdapter?.updateData(list)
                }
            }
        }
    }

    companion object {
        const val TAG = "BOTTOM_SHEET_DIALOG"
    }
}