package com.example.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.webhistory.WebHistoryController
import com.arkivanov.decompose.value.Value
import com.example.util.viewModelStoreOwner
import com.example.decompose.RootComponent.Child
import kotlinx.serialization.Serializable

@OptIn(ExperimentalDecomposeApi::class)
class DefaultRootComponent(
    componentContext: ComponentContext,
    deepLink: DeepLink = DeepLink.None,
    private val webHistoryController: WebHistoryController? = null,
) : RootComponent,
    ComponentContext by componentContext {

    private val nav = StackNavigation<RootConfig>()

    private val _stack: Value<ChildStack<RootConfig, Child>> = childStack(
        source = nav,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.Main,
        childFactory = ::child
    )

    init {
        webHistoryController?.attach(
            navigator = nav,
            serializer = RootConfig.serializer(),
            stack = _stack,
            getPath = Companion::getPathForConfig,
            getConfiguration = Companion::getConfigForPath,
        )
    }

    override val stack: Value<ChildStack<*, Child>> = _stack

    private fun child(config: RootConfig, componentContext: ComponentContext): Child =
        when (config) {
            is RootConfig.Main -> Child.Main(
                MainComponent(
                    componentContext,
                    navigateToDetails = {
                        nav.push(RootConfig.Details)
                    }
                ),
                componentContext.viewModelStoreOwner()
            )

            is RootConfig.Details -> Child.Details(
                DetailsComponent(
                    componentContext,
                    navigateBack = { nav.pop() }
                ),
                componentContext.viewModelStoreOwner()
            )
        }

    override fun onBackClicked() {
        nav.pop()
    }

    private companion object {
        private const val WEB_PATH_DETAILS = "details"

        private fun getPathForConfig(config: RootConfig): String =
            when (config) {
                RootConfig.Main -> ""
                RootConfig.Details -> "/$WEB_PATH_DETAILS"
            }

        private fun getConfigForPath(path: String): RootConfig =
            when (path.removePrefix("/")) {
                WEB_PATH_DETAILS -> RootConfig.Details
                else -> RootConfig.Main
            }
    }
}

@Serializable
private sealed interface RootConfig {
    @Serializable
    data object Main : RootConfig

    @Serializable
    data object Details : RootConfig
}

sealed interface DeepLink {
    data object None : DeepLink
    class Web(val path: String) : DeepLink
}