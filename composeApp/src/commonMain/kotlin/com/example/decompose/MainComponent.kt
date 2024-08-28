package com.example.decompose

import com.arkivanov.decompose.ComponentContext

class MainComponent(
    componentContext: ComponentContext,
    val navigateToDetails: () -> Unit,
) : ComponentContext by componentContext