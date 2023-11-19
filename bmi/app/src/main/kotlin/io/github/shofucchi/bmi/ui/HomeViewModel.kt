package io.github.shofucchi.bmi.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState.Success())
    val uiState = _uiState.asStateFlow()

    fun enterHeight(height: String) {
        _uiState.update { it.copy(height = height) }
    }

    fun enterWeight(weight: String) {
        _uiState.update { it.copy(weight = weight) }
    }

    fun calculateBmi() {
        val fHeight = _uiState.value.height.toFloatOrNull()
        val fWeight = _uiState.value.weight.toFloatOrNull()
        if (fHeight == null || fWeight == null) {
            // TODO: Show error
            return
        }
        val bmi = fWeight / (fHeight / 100.0).pow(2.0)
        _uiState.update {
            it.copy(bmi = bmi.toFloat())
        }
    }

    fun reset() {
        _uiState.update {
            it.copy(height = "", weight = "", bmi = 0.0f)
        }
    }
}