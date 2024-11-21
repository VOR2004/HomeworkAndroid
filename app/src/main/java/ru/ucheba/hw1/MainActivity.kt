package ru.ucheba.hw1

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ucheba.hw1.utils.ScreenTags
import ru.ucheba.hw1.base.BaseActivity
import ru.ucheba.hw1.base.NavigationAction
import ru.ucheba.hw1.databinding.ActivityMainBinding
import ru.ucheba.hw1.multipleTypesList.MultipleTypesFragment

class MainActivity : BaseActivity() {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override val mainContainerId: Int = R.id.main_fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigate(
                destination = MultipleTypesFragment(),
                destinationTag = ScreenTags.MULTIPLE_TYPES_LIST_TAG,
                action = NavigationAction.ADD,
            )
        }
    }
}