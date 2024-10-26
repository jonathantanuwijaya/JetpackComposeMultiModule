package com.learndev.core.utils

sealed class UiEvent {
    object Success : UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
    object NavigateUp : UiEvent()

}