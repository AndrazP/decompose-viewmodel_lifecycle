package com.example

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.decompose.DefaultRootComponent
import com.example.di.commonModule
import com.example.ui.RootScreen
import org.koin.compose.KoinApplication

@Composable
fun App(rootComponent: DefaultRootComponent) {

    KoinApplication(application = {
        modules(commonModule())
    }) {
        MaterialTheme {
            RootScreen(rootComponent)
        }
    }
}