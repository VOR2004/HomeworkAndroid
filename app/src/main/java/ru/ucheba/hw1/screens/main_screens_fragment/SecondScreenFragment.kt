package ru.ucheba.hw1.screens.main_screens_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.base.NavigationAction
import ru.ucheba.hw1.databinding.FragmentScreenSecondBinding

class SecondScreenFragment: Fragment(R.layout.fragment_screen_second) {
    private val viewBinding: FragmentScreenSecondBinding by viewBinding(FragmentScreenSecondBinding::bind)
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

            buttonSecond.setOnClickListener {
                act?.navigate(
                    ThirdScreenFragment(),
                    ThirdScreenFragment.TAG,
                    action = NavigationAction.REPLACE
                )
            }
        }
    }

    companion object {
        const val TAG = "SECOND_SCREEN_FRAGMENT"
        fun getInstance() : SecondScreenFragment {
            return SecondScreenFragment()
        }
    }
}