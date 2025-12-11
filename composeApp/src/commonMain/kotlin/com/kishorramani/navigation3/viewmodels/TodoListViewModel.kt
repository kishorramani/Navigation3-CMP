package com.kishorramani.navigation3.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoListViewModel : ViewModel() {
    private val _todos = MutableStateFlow(
        value = (1..100).map { "Todo $it" }
    )
    val todos = _todos.asStateFlow()

    init {
        println("TodoListViewModel initialized")
    }

    override fun onCleared() {
        super.onCleared()
        println("TodoListViewModel cleared")
    }
}