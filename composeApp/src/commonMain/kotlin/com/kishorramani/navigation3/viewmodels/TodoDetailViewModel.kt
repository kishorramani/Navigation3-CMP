package com.kishorramani.navigation3.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoDetailViewModel(
    private val todo: String
) : ViewModel() {
    private val _state = MutableStateFlow(TodoLDetailState(todo))
    val state = _state.asStateFlow()

    init {
        println("TodoDetailViewModel initialized with todo: $todo")
    }

    override fun onCleared() {
        super.onCleared()
        println("TodoDetailViewModel cleared for todo: $todo")
    }
}

data class TodoLDetailState(
    val todo: String
)