package ru.ucheba.hw1

import android.os.Bundle
import ru.ucheba.hw1.base.BaseActivity
import ru.ucheba.hw1.screens.main_screens_fragment.FirstScreenFragment

class MainActivity : BaseActivity() {
    override val mainContainerId: Int = R.id.main_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigate(
            destination = FirstScreenFragment.getInstance(),
            destinationTag = FirstScreenFragment.TAG)
    }

    private var data: String? = null

    fun setData(data: String?) {
        this.data = data
    }

    fun getData(): String? {
        return data
    }
}