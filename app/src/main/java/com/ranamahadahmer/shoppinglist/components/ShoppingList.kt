package com.ranamahadahmer.shoppinglist.components


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingList() {
    val itemsList = remember { mutableStateListOf<Item>() }
    val itemDialog = remember { mutableStateOf(false) }
    val editingItem = remember { mutableStateOf<Item?>(null) }
    val name = remember { mutableStateOf("") }
    val quantity = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Shopping List") },
                windowInsets = WindowInsets.statusBars
            )
        },
        floatingActionButton = {
            Button(
                onClick = {
                    itemDialog.value = true
                },
                shape = RoundedCornerShape(20)
            ) {
                Text(text = "Add")
            }
        }) {
        LazyColumn(
            modifier = Modifier
                    .padding(it)
                    .padding(bottom = 80.dp),
        ) {
            items(itemsList) { item ->
                ShoppingItem(item = item,
                    edit = { editingItem.value = item },
                    delete = { itemsList.remove(item) })

            }
        }

        if (itemDialog.value || editingItem.value != null) {
            name.value = ""
            quantity.value = ""

            if (editingItem.value != null) {
                name.value = editingItem.value!!.name
                quantity.value = editingItem.value!!.quantity.toString()
            }
            AddItem(itemDialog = itemDialog,
                editingItem = editingItem,
                name = name,
                quantity = quantity,
                addClick = {
                    if (name.value.isNotEmpty() && editingItem.value == null) {
                        itemsList.add(
                            Item(
                                id = itemsList.size + 1,
                                name = name.value,
                                quantity = quantity.value.toIntOrNull() ?: 0
                            )
                        )
                        itemDialog.value = false
                        editingItem.value = null
                    } else if (name.value.isNotEmpty() && editingItem.value != null) {
                        itemsList[itemsList.indexOf(editingItem.value)] = Item(
                            id = editingItem.value!!.id,
                            name = name.value,
                            quantity = quantity.value.toIntOrNull() ?: 0
                        )
                        itemDialog.value = false
                        editingItem.value = null
                    }
                })


        }
    }


}


@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_6_PRO, showBackground = true)
fun ProjectPreview() {
    ShoppingList()
}

