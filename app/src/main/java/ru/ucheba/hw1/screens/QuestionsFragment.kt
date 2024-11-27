package ru.ucheba.hw1.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ucheba.hw1.R
import ru.ucheba.hw1.adapter.recycler.AdapterRV
import ru.ucheba.hw1.databinding.FragmentQuestionsBinding
import ru.ucheba.hw1.repository.QuestionsAnswersRepository

class QuestionsFragment: Fragment(R.layout.fragment_questions) {

    private val viewBinding: FragmentQuestionsBinding by viewBinding(FragmentQuestionsBinding::bind)

    private var adapterRV: AdapterRV? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(POSITION_KEY)?.let { position ->
            adapterRV = AdapterRV(
                question = QuestionsAnswersRepository.list[position],
                onCheck = ::onCheck
                )
            val quest: String = QuestionsAnswersRepository.list[position].question
            viewBinding.question.text = quest

            with(viewBinding) {
                answerList.adapter = adapterRV
                answerList.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }


    private fun onCheck(position: Int) {
        if (!viewBinding.answerList.isComputingLayout) {
            val prev = QuestionsAnswersRepository.list[QuestionsAnswersRepository.currentPage].selected
            QuestionsAnswersRepository.list[QuestionsAnswersRepository.currentPage].selected = position
            if (prev != null) {
                adapterRV?.notifyItemChanged(prev, position)
            }
        }
    }


    companion object {
        private const val POSITION_KEY = "POSITION"

        fun getInstance(position: Int) = QuestionsFragment().apply {
            arguments = bundleOf(POSITION_KEY to position)
        }
    }
}