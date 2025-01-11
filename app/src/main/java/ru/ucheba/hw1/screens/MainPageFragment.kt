package ru.ucheba.hw1.screens

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.adapter.recycler.AdapterRecyclerViewThemes
import ru.ucheba.hw1.adapter.spinner.AdapterImportanceSpinner
import ru.ucheba.hw1.databinding.FragmentMainPageBinding
import ru.ucheba.hw1.key.Keys
import ru.ucheba.hw1.model.NotificationData
import ru.ucheba.hw1.model.NotificationImportance
import ru.ucheba.hw1.repository.ThemeRepository
import ru.ucheba.hw1.utils.NotificationHandler
import ru.ucheba.hw1.utils.PermissionHandler

class MainPageFragment: Fragment(R.layout.fragment_main_page) {

    private val viewBinding: FragmentMainPageBinding by viewBinding(FragmentMainPageBinding::bind)

    private var notificationHandler: NotificationHandler? = null

    private var notificationImportance = NotificationImportance.DEFAULT

    private var notificationChannelId = 0

    private val themesList = ThemeRepository.themes

    private var currentTheme = R.style.Theme_MyApplication

    private var imageUri: Uri? = null

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                imageUri = uri
                viewBinding.avatar.setImageURI(imageUri)
                viewBinding.btnDelete.visibility = View.VISIBLE
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (notificationHandler == null) {
            notificationHandler =
                (requireActivity() as? MainActivity)?.notificationHandler
        }
        askForPermission()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationHandler = null
    }

    private fun initViews() {

        val spinnerAdapter = AdapterImportanceSpinner(
            requireContext(),
            NotificationImportance.entries
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val item = parent.getItemAtPosition(position) as NotificationImportance
                    when(item) {
                        NotificationImportance.MAX -> {
                            notificationImportance = NotificationImportance.MAX
                            notificationChannelId = resources.getInteger(R.integer.private_channel_id)
                        }
                        NotificationImportance.LOW -> {
                            notificationImportance = NotificationImportance.LOW
                            notificationChannelId = resources.getInteger(R.integer.low_channel_id)
                        }
                        NotificationImportance.HIGH -> {
                            notificationImportance = NotificationImportance.HIGH
                            notificationChannelId = resources.getInteger(R.integer.urgent_channel_id)
                        }
                        NotificationImportance.DEFAULT -> {
                            notificationImportance = NotificationImportance.DEFAULT
                            notificationChannelId = resources.getInteger(R.integer.default_channel_id)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        with(viewBinding) {

            importanceSpinner.adapter = spinnerAdapter
            importanceSpinner.onItemSelectedListener = itemSelectedListener

            themesRv.adapter = AdapterRecyclerViewThemes(themesList, ::onClick)
            themesRv.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )

            btnShowThemes.setOnClickListener {
                if (themesRv.visibility == View.GONE) {
                    btnShowThemes.setImageResource(R.drawable.baseline_keyboard_double_arrow_up_24)
                    themesRv.visibility = View.VISIBLE
                }
                else {
                    btnShowThemes.setImageResource(R.drawable.baseline_keyboard_double_arrow_down_24)
                    themesRv.visibility = View.GONE
                }
            }

            btnShowNotification.setOnClickListener {
                val textEt = textInputEtTitle.text
                val textInputEt = textInputEtNotification.text
                if (textEt.isNullOrBlank()) {
                    Toast.makeText(requireContext(), R.string.toast_empty_title, Toast.LENGTH_SHORT).show()
                }
                else if (textInputEt.isNullOrBlank()) {
                    Toast.makeText(requireContext(), R.string.toast_empty_text, Toast.LENGTH_SHORT).show()
                }

                else {
                    if (notificationHandler == null) {
                        notificationHandler = (requireActivity() as? MainActivity)?.notificationHandler
                    }
                    notificationHandler?.showNotification(
                        data = NotificationData(
                            id = notificationChannelId,
                            title = textEt.toString(),
                            message = textInputEt.toString(),
                            notificationType = notificationImportance
                        )
                    )
                }
            }
            btnResetColor.setOnClickListener{
                resetColorTheme()
            }

            imgBtnAvatar.setOnClickListener {
                if (imageUri != null) {
                    Toast.makeText(
                        requireContext(),
                        R.string.image_already_selected,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    selectImage()
                }
            }

            btnDelete.setOnClickListener {
                avatar.setImageURI(null)
                btnDelete.visibility = View.GONE
            }
        }
    }

    private fun onClick(position: Int) {
        val curStyle = themesList[position].getStyle()
        currentTheme = curStyle
        changeCurrentTheme(currentTheme)
    }

    private fun resetColorTheme() {
        changeCurrentTheme(null)
    }

    private fun changeCurrentTheme(theme: Int?) {
        (requireActivity() as? MainActivity)?.intent?.removeExtra(Keys().notificationOpened)
        (requireActivity() as? MainActivity)?.changeTheme(theme)
        (requireActivity() as? MainActivity)?.recreate()
    }

    private fun askForPermission() {
        val activity = (requireActivity() as AppCompatActivity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissionHandler = PermissionHandler()
            permissionHandler.initContracts(activity = activity)
            if (
                activity.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED ||
                activity.checkSelfPermission(android.Manifest.permission.READ_MEDIA_IMAGES) !=
                PackageManager.PERMISSION_GRANTED
                )
            {
                permissionHandler.requestMultiplePermissions(
                    listOf(
                        android.Manifest.permission.POST_NOTIFICATIONS,
                        android.Manifest.permission.READ_MEDIA_IMAGES,
                    )
                )
            }
        }
    }

    private fun selectImage() {
        pickMedia.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    companion object {
        const val TAG = "MAIN_PAGE_FRAGMENT"
    }

}