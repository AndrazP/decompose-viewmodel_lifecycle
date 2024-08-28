package com.example.decompose

import androidx.lifecycle.ViewModelStoreOwner
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner

interface RootComponent : BackHandlerOwner {

    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed class Child(open val viewModelStore: ViewModelStoreOwner) {

        class Main(
            val component: MainComponent,
            override val viewModelStore: ViewModelStoreOwner,
        ) : Child(viewModelStore)

        class Details(
            val component: DetailsComponent,
            override val viewModelStore: ViewModelStoreOwner,
        ) : Child(viewModelStore)
    }
}
