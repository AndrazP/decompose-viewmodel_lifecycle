package com.example.decompose

import com.arkivanov.decompose.ComponentContext

class DetailsComponent(
    componentContext: ComponentContext,
    val navigateBack: () -> Unit,
) : ComponentContext by componentContext