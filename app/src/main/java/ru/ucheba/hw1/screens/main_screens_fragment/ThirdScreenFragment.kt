package ru.ucheba.hw1.screens.main_screens_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.databinding.FragmentScreenThirdBinding

class ThirdScreenFragment: Fragment(R.layout.fragment_screen_third) {
    private val viewBinding: FragmentScreenThirdBinding by viewBinding(FragmentScreenThirdBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val act = (requireActivity() as? MainActivity)
        with(viewBinding) {

            if (!act?.getData().isNullOrBlank()) {
            headerTv.text = act?.getData()
            }
        }
    }

    companion object {
        const val TAG = "THIRD_SCREEN_FRAGMENT"
        fun getInstance() : ThirdScreenFragment {
            return ThirdScreenFragment()
        }
    }
}