package com.ranamahadahmer.shoppinglist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddItem(itemDialog: MutableState<Boolean>,
            editingItem: MutableState<Item?>,
            name: MutableState<String>,
            quantity: MutableState<String>,
            addClick: () -> Unit) {
    AlertDialog(
        title = { Text(text = "Shopping Item") },
        onDismissRequest = {
            itemDialog.value = false
            editingItem.value = null
        },


        confirmButton = {
            Column {
                TextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = { Text(text = "Name") }
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                TextField(
                    value = quantity.value,
                    onValueChange = { quantity.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Quantity") }
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { itemDialog.value = false }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = addClick) {
                        Text(text = if (editingItem.value == null) "Add" else "Update")
                    }
                }

            }
        },
    )
}