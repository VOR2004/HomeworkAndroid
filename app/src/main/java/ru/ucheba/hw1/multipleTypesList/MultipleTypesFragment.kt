package ru.ucheba.hw1.multipleTypesList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.adapter.recycler.AdapterWithMultipleHolders
import ru.ucheba.hw1.base.NavigationAction
import ru.ucheba.hw1.databinding.FragmentMultipleTypesRvBinding
import ru.ucheba.hw1.model.SecondHolderData
import ru.ucheba.hw1.repository.ScreensContentRepository
import ru.ucheba.hw1.screens.SecondScreenFragment
import ru.ucheba.hw1.screens.bottom_sheet.BottomSheetFragment
import ru.ucheba.hw1.ui.decorators.SimpleHorizontalDecorator
import ru.ucheba.hw1.utils.LayoutChanger
import ru.ucheba.hw1.utils.getValueInDp


class MultipleTypesFragment : Fragment(R.layout.fragment_multiple_types_rv) {

    private val viewBinding by viewBinding(FragmentMultipleTypesRvBinding::bind)

    var rvAdapter: AdapterWithMultipleHolders? = null

    private var state = "LINEAR"

    val list = ScreensContentRepository.getListForMultipleTypes().toMutableList()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvAdapter = AdapterWithMultipleHolders(
            requestManager = Glide.with(requireContext()),
            items = list,
            action = {onItemClick(it)},
            onButtonClickLinear = ::onButtonClickLinear,
            onButtonClickGrid = ::onButtonClickGrid
        )

        with(viewBinding) {
            mainRecycler.adapter = rvAdapter
            mainRecycler.layoutManager =
                LayoutChanger().changeLayoutRvToLinear(requireContext())
            mainRecycler.addItemDecoration(
                SimpleHorizontalDecorator(
                    marginValue = getValueInDp(value = 16f, requireContext()).toInt()
                )
            )


            fab.setOnClickListener {
                val dialog = BottomSheetFragment().apply {
                    isCancelable = true
                }
                dialog.show(childFragmentManager, BottomSheetFragment.TAG)
            }

        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
            with(viewBinding) {
                if (state != "LINEAR") {
                    mainRecycler.layoutManager = LayoutChanger().changeLayoutRvToGrid(
                        requireContext()
                    )
                }
                else {
                    mainRecycler.layoutManager =
                        LayoutChanger().changeLayoutRvToLinear(
                            requireContext()
                        )
                }
            }
        }

    private fun onItemClick(it: SecondHolderData) {
        val bundle = Bundle()
        bundle.putString("ARG_DESC", it.descText)
        bundle.putString("ARG_IMAGE", it.imageUrl)
        bundle.putString("ARG_HEADER", it.headerText)
        (requireActivity() as? MainActivity)?.navigate(
            destination = SecondScreenFragment.getInstance(bundle),
            destinationTag = SecondScreenFragment.TAG,
            action = NavigationAction.REPLACE
            )
    }

    private fun onButtonClickGrid() {
        state = "GRID"
        with(viewBinding) {
            mainRecycler.layoutManager = LayoutChanger().changeLayoutRvToGrid(
                requireContext()
            )
        }
    }

    private fun onButtonClickLinear() {
        state = "LINEAR"
        with(viewBinding) {
            mainRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }
}