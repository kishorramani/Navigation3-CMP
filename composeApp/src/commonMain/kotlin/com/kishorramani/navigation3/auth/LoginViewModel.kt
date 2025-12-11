package com.kishorramani.navigation3.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    init {
        println("LoginViewModel initialized")
    }

    fun bump() {
        _counter.value++
    }

    override fun onCleared() {
        super.onCleared()
        println("LoginViewModel cleared")
    }
}