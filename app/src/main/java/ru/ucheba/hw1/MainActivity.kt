package ru.ucheba.hw1

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.ucheba.hw1.viewmodel.AppViewModel
import ru.ucheba.hw1.screens.compose.App

class MainActivity : ComponentActivity() {

    class UserViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppViewModel(application) as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val owner = LocalViewModelStoreOwner.current

            owner?.let {
                val viewModel: AppViewModel = viewModel(
                    it,
                    KeyNames.KEY_VM,
                    UserViewModelFactory(LocalContext.current.applicationContext as Application)
                )
                App(viewModel)
            }
        }
    }
}
