package ru.ucheba.hw1.screens.compose.ui.radiogroup

import CoroutineManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R
import ru.ucheba.hw1.repository.Constants
import ru.ucheba.hw1.utils.CoroutineCancellationMode
import ru.ucheba.hw1.utils.CoroutineExecutionMode

@Composable
fun RadioButtonSingleSelection(modifier: Modifier = Modifier, coroutineManager: CoroutineManager) {
    val radioOptions = Constants.listOfModes
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Text(
        text = stringResource(R.string.how_to_start),
        modifier = Modifier
            .padding(start = 24.dp, top = 32.dp, bottom = 16.dp),
        color = colorResource(R.color.default_gray_text)
    )

    Column(modifier.selectableGroup()) {
        radioOptions.forEach { rbDataOption ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .height(56.dp)
                    .selectable(
                        selected = (rbDataOption == selectedOption),
                        onClick = {
                            onOptionSelected(rbDataOption)
                            if (rbDataOption.option == CoroutineExecutionMode.SEQUENTIAL) {
                                coroutineManager
                                    .setCoroutineExecutionMode(
                                        CoroutineExecutionMode.SEQUENTIAL
                                    )
                            } else {
                                coroutineManager
                                    .setCoroutineExecutionMode(
                                        CoroutineExecutionMode.PARALLEL
                                    )
                            }
                            },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (rbDataOption == selectedOption),
                    onClick = null
                )
                Text(
                    text = stringResource(rbDataOption.name),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun RadioButtonSingleSelection2(modifier: Modifier = Modifier, coroutineManager: CoroutineManager) {
    val radioOptions = Constants.listOfOptions
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Text(
        text = stringResource(R.string.choose_cor_logic),
        modifier = Modifier
            .padding(start = 24.dp, top = 24.dp, bottom = 16.dp),
        color = colorResource(R.color.default_gray_text)
    )

    Column(modifier.selectableGroup()) {
        radioOptions.forEach { rbDataMode ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .height(56.dp)
                    .selectable(
                        selected = (rbDataMode == selectedOption),
                        onClick = {
                            onOptionSelected(rbDataMode)
                            if (rbDataMode.mode == CoroutineCancellationMode.CANCEL_ON_BACKGROUND) {
                                coroutineManager.setCoroutineCancellationMode(
                                    CoroutineCancellationMode.CANCEL_ON_BACKGROUND
                                )
                            } else {
                                coroutineManager.setCoroutineCancellationMode(
                                    CoroutineCancellationMode.CONTINUE_ON_BACKGROUND
                                )
                            }
                                  },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (rbDataMode == selectedOption),
                    onClick = null
                )
                Text(
                    text = stringResource(rbDataMode.name),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}