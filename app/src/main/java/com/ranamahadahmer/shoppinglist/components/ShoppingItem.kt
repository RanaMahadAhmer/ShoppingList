package com.ranamahadahmer.shoppinglist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ShoppingItem(item: Item, edit: () -> Unit, delete: () -> Unit) {
    OutlinedCard(
        onClick = { }, modifier = Modifier
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
                IconButton(onClick = edit) {
                    Icon(imageVector = Icons.TwoTone.Edit, contentDescription = "No")
                }
                IconButton(onClick = delete) {
                    Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "No")
                }
            }
        }
    }
}