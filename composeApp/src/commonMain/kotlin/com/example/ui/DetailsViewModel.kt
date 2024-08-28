package com.example.ui

import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

    init {
        println("DetailsViewModel init")
    }

    override fun onCleared() {
        println("DetailsViewModel onCleared")
        super.onCleared()
    }
}