package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.decompose.MainComponent
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun MainScreen(
    component: MainComponent,
    viewModel: MainViewModel = koinViewModel()
) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Main Screen")
        Button(onClick = { component.navigateToDetails() }) {
            Text("Next screen")
        }
    }
}