package com.example.util

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.InstanceKeeperOwner
import com.arkivanov.essenty.instancekeeper.getOrCreate

/**
 * By default Decompose doesn't create separate ViewModelStoreOwner for every child screen.
 * https://arkivanov.github.io/Decompose/tips-tricks/composable-viewmodel/
 */
internal fun InstanceKeeperOwner.viewModelStoreOwner(): ViewModelStoreOwner =
    instanceKeeper.getOrCreate(::ViewModelStoreOwnerInstance)

private class ViewModelStoreOwnerInstance :
    ViewModelStoreOwner,
    InstanceKeeper.Instance {
    override val viewModelStore: ViewModelStore = ViewModelStore()

    override fun onDestroy() {
        viewModelStore.clear()
    }
}
