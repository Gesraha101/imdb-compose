package com.example.coreui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

interface SpinnerOption { val value: String }

@Composable
fun Spinner(
    options: Array<SpinnerOption>,
    selectedItem: SpinnerOption,
    onItemSelected: (SpinnerOption) -> Unit
) {
    CustomSpinner(
        modifier = Modifier.wrapContentSize(),
        dropDownModifier = Modifier.wrapContentSize(),
        items = options,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        selectedItemFactory = { modifier, item ->
            Row(
                modifier = modifier
                    .padding(8.dp)
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.value, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Icon(
                    Icons.Outlined.KeyboardArrowDown,
                    "list",
                    tint = Color.White
                )
            }
        },
        dropdownItemFactory = { item, _ ->
            Text(text = item.value, color = Color.White)
        }
    )
}

@Preview
@Composable
fun Icon() {
    Icon(
        Icons.Outlined.MoreVert,
        "list",
        tint = Color.White
    )
}

@Composable
private fun <T> CustomSpinner(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: Array<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    selectedItemFactory: @Composable (Modifier, T) -> Unit,
    dropdownItemFactory: @Composable (T, Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.TopStart)) {
        selectedItemFactory(
            Modifier
                .clickable { expanded = true },
            selectedItem
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = dropDownModifier
        ) {
            items.forEachIndexed { index, element ->
                DropdownMenuItem(onClick = {
                    onItemSelected(items[index])
                    expanded = false
                }) {
                    dropdownItemFactory(element, index)
                }
            }
        }
    }
}