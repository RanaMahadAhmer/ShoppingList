package com.ranamahadahmer.shoppinglist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Item(val id: Int, var name: String, val quantity: Int)

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Project() {
    val itemsList = remember { mutableStateListOf<Item>() }
    var itemDialog by remember { mutableStateOf(false) }
    var editingItem by remember { mutableStateOf<Item?>(null) }



    itemsList.add(Item(1, "I am Mahad", 0))
    itemsList.add(Item(1, "I am Mahad", 2))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))
    itemsList.add(Item(1, "I am Mahad", 1))

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
                    itemDialog = true
                },
                shape = RoundedCornerShape(20)
            ) {
                Text(text = "Add")
            }
        }) { it ->
        LazyColumn(
            modifier = Modifier
                    .padding(it)
                    .padding(bottom = 80.dp),
        ) {
            items(itemsList) { item ->
                OutlinedCard(
                    onClick = { /*TODO*/ }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text(text = item.name)
                            Text(text = item.quantity.toString())
                        }
                        Row(modifier = Modifier.padding(16.dp)) {
                            IconButton(onClick = { editingItem = item }) {
                                Icon(imageVector = Icons.TwoTone.Edit, contentDescription = "No")

                            }

                            IconButton(onClick = { itemsList.remove(item) }) {

                                Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "No")
                            }


                        }
                    }

                }

            }
        }

        if (itemDialog || editingItem != null) {
            var name by remember { mutableStateOf("") }
            var quantity by remember { mutableIntStateOf(0) }

            if (editingItem != null) {
                name = editingItem!!.name
                quantity = editingItem!!.quantity
            }

            AlertDialog(
                onDismissRequest = {
                    itemDialog = false
                    editingItem = null
                },


                confirmButton = {
                    Column {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(text = "Name") }
                        )
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        TextField(
                            value = quantity.toString(),
                            onValueChange = { quantity = it.toInt() },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Quantity") }
                        )
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = { itemDialog = false }) {
                                Text(text = "Cancel")
                            }
                            Button(onClick = {
                                if (name.isNotEmpty() && editingItem == null) {
                                    itemsList.add(
                                        Item(
                                            id = itemsList.size + 1,
                                            name = name,
                                            quantity = quantity
                                        )
                                    )
                                    itemDialog = false
                                    editingItem = null
                                } else if (name.isNotEmpty() && editingItem != null) {
                                    itemsList[itemsList.indexOf(editingItem!!)] = Item(
                                        id = editingItem!!.id,
                                        name = name,
                                        quantity = quantity
                                    )
                                    itemDialog = false
                                    editingItem = null
                                }
                            }) {
                                Text(text = if (editingItem == null) "Add" else "Update")
                            }
                        }

                    }
                },
            )
        }
    }


}


@Composable
@Preview(showSystemUi = true, device = Devices.PIXEL_6_PRO, showBackground = true)
fun ProjectPreview() {
    Project()
}

