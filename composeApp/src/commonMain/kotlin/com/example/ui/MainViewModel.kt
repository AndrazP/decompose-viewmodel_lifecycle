package com.example.ui

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    init {
        println("MainViewModel init")
    }

    override fun onCleared() {
        println("MainViewModel onCleared")
        super.onCleared()
    }
}