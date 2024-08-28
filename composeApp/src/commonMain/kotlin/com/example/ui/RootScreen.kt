package com.example.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.example.decompose.RootComponent


@Composable
internal fun RootScreen(
    component: RootComponent,
) {
    MaterialTheme {
        Children(
            component = component
        )
    }
}

@OptIn(ExperimentalDecomposeApi::class)
@Composable
private fun Children(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation { child, otherChild, _ ->
                slide(animationSpec = tween(easing = LinearEasing)) + fade()
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val child = it.instance
            CompositionLocalProvider(LocalViewModelStoreOwner provides child.viewModelStore) {
                when (child) {
                    is RootComponent.Child.Main -> {
                        MainScreen(child.component)
                    }

                    is RootComponent.Child.Details -> {
                        DetailsScreen(child.component)
                    }
                }
            }
        }
    }
}