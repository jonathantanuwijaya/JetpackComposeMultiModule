package com.learndev.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learndev.core.domain.preferences.Preferences
import com.learndev.core.domain.usecases.FilterOutDigits
import com.learndev.core.utils.UiEvent
import com.learndev.core.utils.UiText
import com.learndev.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {
    var height by mutableStateOf("170")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onHeightEnter(height: String) {
        if (height.length <= 3) {
            this.height = filterOutDigits(height)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber =
                height.toIntOrNull() ?: kotlin.run {
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            UiText.StringResource(
                                R.string.error_height_cant_be_empty
                            )
                        )
                    )
                    return@launch
                }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvent.Success)
        }
    }
}