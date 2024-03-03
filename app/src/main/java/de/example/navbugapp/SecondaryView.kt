package de.example.navbugapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondaryView(mainCoordinator: MainCoordinator) {
    Column {
        Text(
            text = "SecondaryView",
            modifier = Modifier
        )
        Button(onClick = {
            mainCoordinator.toPrimary()
        }) {
            Text("Back")
        }
    }
}