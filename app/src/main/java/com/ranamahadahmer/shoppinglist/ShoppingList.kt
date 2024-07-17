package com.ranamahadahmer.shoppinglist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class Item(val id: Int, var name: String, val quantity: Int)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Project() {
    val itemsList = remember { mutableStateListOf<Item>() }
    var showNewItemDialog by remember { mutableStateOf(false) }
    val selected by remember { mutableStateOf(null) }




    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        Button(onClick = {
            showNewItemDialog = true
        }, shape = RoundedCornerShape(20)) {
            Text(text = "Add")
        }
    }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 40.dp),
            horizontalAlignment = Alignment.Start
        ) {
            items(itemsList) { item ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Item ${item.id}")
                    Text(text = "Name: ${item.name}")
                    Text(text = "Quantity: ${item.quantity}")
                }
            }
        }

        if (showNewItemDialog) {
            var name by remember { mutableStateOf("") }
            var quantity by remember { mutableIntStateOf(1) }
            AlertDialog(
                onDismissRequest = { showNewItemDialog = false },
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
                            
                            onValueChange = { quantity = it.toIntOrNull() ?: 1 },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            label = { Text(text = "Quantity") }
                        )
                        Spacer(modifier = Modifier.padding(vertical = 8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = { showNewItemDialog = false }) {
                                Text(text = "Cancel")
                            }
                            Button(onClick = {
                                if (name.isNotEmpty()) {
                                    itemsList.add(
                                        Item(
                                            id = itemsList.size + 1,
                                            name = name,
                                            quantity = quantity
                                        )
                                    )
                                    showNewItemDialog = false
                                }
                            }) {
                                Text(text = "Add")
                            }
                        }

                    }
                },
            )
        }
    }


}






