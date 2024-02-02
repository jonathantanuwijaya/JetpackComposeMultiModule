package com.learndev.calorytracker.navigation

import androidx.navigation.NavController
import com.learndev.core.utils.UiEvent

fun NavController.navigate(event: UiEvent.Navigate){
    this.navigate(event.route)
}