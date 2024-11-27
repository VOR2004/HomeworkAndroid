package ru.ucheba.hw1.screens

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import ru.ucheba.hw1.R
import ru.ucheba.hw1.adapter.viewpager.ViewPagerAdapter
import ru.ucheba.hw1.databinding.FragmentViewPagerBinding
import ru.ucheba.hw1.repository.QuestionsAnswersRepository

class ViewPagerFragment: Fragment(R.layout.fragment_view_pager) {
    private val viewBinding: FragmentViewPagerBinding by viewBinding(FragmentViewPagerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagesCount = QuestionsAnswersRepository.getListQuestions().size
        val adapter = ViewPagerAdapter(
            manager = parentFragmentManager,
            lifecycle = this.lifecycle
        )
        viewBinding.contentVp.adapter = adapter

        with(viewBinding) {

            previousButton.setOnClickListener {
                if(contentVp.currentItem > 0) {
                    contentVp.currentItem--
                }
            }

            nextButton.setOnClickListener {
                if(contentVp.currentItem == pagesCount - 1) {
                    Snackbar.make(contentVp, "Ответы приняты", Snackbar.LENGTH_SHORT).setAnchorView(nextButton).show()
                } else {
                    contentVp.currentItem ++
                }
            }

            contentVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    val colorEnd = 0xFF00FF00.toInt()

                    QuestionsAnswersRepository.currentPage = position

                    previousButton.isEnabled = position > 0

                    if (position == pagesCount - 1) {
                        nextButton.text = getString(R.string.finish)
                        nextButton.setBackgroundColor(colorEnd)

                    } else {
                        getContext()?.let { ContextCompat.getColor(it, R.color.purple_500) }
                            ?.let { nextButton.setBackgroundColor(it) }

                        nextButton.text = getString(R.string.nextBtn)
                    }

                    nextButton.isEnabled = (QuestionsAnswersRepository.allchecked() || position < pagesCount - 1)

                    counter.text = buildString {
                        append((position + 1).toString())
                        append("/")
                        append(pagesCount)
                    }
                }
            })

        }
    }

    companion object {
        const val TAG = "VP_FRAGMENT"
    }
}