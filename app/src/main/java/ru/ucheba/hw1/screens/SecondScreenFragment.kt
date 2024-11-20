package ru.ucheba.hw1.screens

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.base.NavigationAction
import ru.ucheba.hw1.databinding.FragmentSecondScreenBinding
import ru.ucheba.hw1.multipleTypesList.MultipleTypesFragment
import ru.ucheba.hw1.utils.ScreenTags

class SecondScreenFragment: Fragment(R.layout.fragment_second_screen) {
    private val viewBinding: FragmentSecondScreenBinding by viewBinding(FragmentSecondScreenBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            textDescr.text = arguments?.getString("ARG_DESC") ?: "ERR"
            pageHeaderTv.text = arguments?.getString("ARG_HEADER") ?: "ERR"
            val urlImage = arguments?.getString("ARG_IMAGE") ?: "ERR"
            Glide.with(this@SecondScreenFragment).load(urlImage).into(imageSecondScreen)
        }
    }

    companion object {
        const val TAG = "SECOND_SCREEN_FRAGMENT"
        fun getInstance(bundle: Bundle): SecondScreenFragment {
            return SecondScreenFragment().apply {
                arguments = bundle
            }
        }
    }
}