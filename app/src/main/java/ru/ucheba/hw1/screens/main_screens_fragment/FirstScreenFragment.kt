package ru.ucheba.hw1.screens.main_screens_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.base.NavigationAction
import ru.ucheba.hw1.databinding.FragmentScreenFirstBinding

class FirstScreenFragment: Fragment(R.layout.fragment_screen_first) {
    private val viewBinding: FragmentScreenFirstBinding by viewBinding(FragmentScreenFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val act = (requireActivity() as? MainActivity)
        with(viewBinding) {
            buttonFirst.setOnClickListener {
                act?.setData(textInputEt.text.toString())
                act?.navigate(
                    SecondScreenFragment(),
                    SecondScreenFragment.TAG,
                    action = NavigationAction.REPLACE
                )
            }

            buttonSecond.setOnClickListener {
                act?.setData(textInputEt.text.toString())
                act?.navigate(
                    SecondScreenFragment(),
                    SecondScreenFragment.TAG,
                    action = NavigationAction.REPLACE

                )

                act?.navigate(
                    ThirdScreenFragment(),
                    ThirdScreenFragment.TAG,
                    action = NavigationAction.REPLACE
                )
            }
        }
    }

    companion object {
        const val TAG = "FIRST_SCREEN_FRAGMENT"
        fun getInstance() : FirstScreenFragment {
            return FirstScreenFragment()
        }
    }


}