package com.kyakubov.swapbarcompose


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties

@Composable
fun <T: Displayable> SelectButton(
    modifier: Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    options: List<T> = listOf(),
    selectedItem: T,
    onSelect: (T) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box() {
            Button(
                colors = buttonColors,
                onClick = {
                    expanded = !expanded
                }
            ) {
                Text(selectedItem.displayName)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null
                )
            }
            DropdownMenu(
                modifier = Modifier.testTag("drop_down_menu"),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }, properties = PopupProperties(focusable = false)
            ) {
                options.sortedBy { if (it == selectedItem) 0 else 1 }.forEach { label ->
                    DropdownMenuItem(text = {
                        Text(text = label.displayName)
                    }, onClick = {

                        onSelect.invoke(label)
                        expanded =  false
                    })
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun SelectButton_Previews() {
//    SelectButton(modifier = Modifier.fillMaxSize(), LanguageType.entries, mutableStateOf(LanguageType.UZBEK)) {
//
//    }
}