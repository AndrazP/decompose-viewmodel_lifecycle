package com.example.di


import com.example.ui.DetailsViewModel
import com.example.ui.MainViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun commonModule() = module {
    viewModel { MainViewModel() }
    viewModel { DetailsViewModel() }
}