package ru.ucheba.hw1.screens.compose.ui.dropdown

import CoroutineManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.ucheba.hw1.R
import ru.ucheba.hw1.repository.Constants
import ru.ucheba.hw1.utils.CoroutinePool

@Composable
fun LongBasicDropdownMenu(cm: CoroutineManager) {
    var expanded by remember { mutableStateOf(false) }
    val menuItemData = Constants.listOfPool

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        TextButton(
            onClick = { expanded = !expanded },
        ) {
            Icon(
                Icons.Default.Menu,
                contentDescription = stringResource(R.string.more_options)
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.pool_threads)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            val lastIndex = menuItemData.lastIndex
            menuItemData.forEachIndexed { index, option ->
                DropdownMenuItem(
                    modifier = Modifier.height(40.dp),
                    text = { Text(stringResource(option.name)) },
                    onClick = {
                        when (option.thread) {
                            CoroutinePool.IO -> cm.setCoroutinePool(CoroutinePool.IO)
                            CoroutinePool.DEFAULT -> cm.setCoroutinePool(CoroutinePool.DEFAULT)
                            CoroutinePool.UNCONFINED -> cm.setCoroutinePool(CoroutinePool.UNCONFINED)
                            CoroutinePool.MAIN -> cm.setCoroutinePool(CoroutinePool.MAIN)
                            null -> cm.setCoroutinePool(CoroutinePool.DEFAULT)
                        }
                        expanded = false
                    }
                )
                if (index < lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}