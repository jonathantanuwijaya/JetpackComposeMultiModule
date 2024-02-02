package com.learndev.core.utils

sealed class UiEvent {
    data class Navigate(val route:String):UiEvent()
    object NavigateUp: UiEvent()

}