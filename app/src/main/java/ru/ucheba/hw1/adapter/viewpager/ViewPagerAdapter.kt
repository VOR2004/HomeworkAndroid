package ru.ucheba.hw1.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.ucheba.hw1.screens.QuestionsFragment


class ViewPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount(): Int = 12

    override fun createFragment(position: Int): Fragment {
        return QuestionsFragment.getInstance(position)
    }
}