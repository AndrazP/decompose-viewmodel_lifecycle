package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.decompose.DetailsComponent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailsScreen(
    component: DetailsComponent,
    viewModel: DetailsViewModel = koinViewModel()
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Details screen")
        Button(onClick = { component.navigateBack() }) {
            Text("Back")
        }
    }
}