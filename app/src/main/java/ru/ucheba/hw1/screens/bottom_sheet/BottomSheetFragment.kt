package ru.ucheba.hw1.screens.bottom_sheet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.databinding.FragmentBottomSheetBinding
import ru.ucheba.hw1.screens.main_screens_fragment.FirstScreenFragment

class BottomSheetFragment: BottomSheetDialogFragment(R.layout.fragment_bottom_sheet) {

    private val viewBinding: FragmentBottomSheetBinding by viewBinding(FragmentBottomSheetBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            textInputEtBs.doOnTextChanged { _, _, _, _ ->
                bsBtn.isEnabled = textInputEtBs.text.toString().isNotBlank()
            }

            bsBtn.setOnClickListener {
                val text = textInputEtBs.text.toString()
                (parentFragment as FirstScreenFragment).passData(text)
            }
        }
    }

    companion object {
        const val TAG = "BOTTOM_SHEET_DIALOG"
    }
}