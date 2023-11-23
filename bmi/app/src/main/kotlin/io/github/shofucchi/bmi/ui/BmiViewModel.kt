package io.github.shofucchi.bmi.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.pow

class BmiViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BmiUiState())
    val uiState = _uiState.asStateFlow()

    fun enterHeight(height: String) {
        val errorReason = isInvalidHeight(height)
        if (errorReason != ErrorReason.NONE) {
            _uiState.update { it.copy(height = height, errorReasonHeight = errorReason) }
        } else {
            _uiState.update { it.copy(height = height, errorReasonHeight = ErrorReason.NONE) }
        }
    }

    fun enterWeight(weight: String) {
        val errorReason = isInvalidWeight(weight)
        if (errorReason != ErrorReason.NONE) {
            _uiState.update { it.copy(weight = weight, errorReasonWeight = errorReason) }
        } else {
            _uiState.update { it.copy(weight = weight, errorReasonWeight = ErrorReason.NONE) }
        }
    }

    fun calculateBmi() {
        val fHeight = _uiState.value.height.toFloat()
        val fWeight = _uiState.value.weight.toFloat()
        val bmi = fWeight / (fHeight / 100.0).pow(2.0)
        _uiState.update {
            it.copy(bmi = bmi.toFloat())
        }
    }

    fun reset() {
        _uiState.update {
            it.copy(
                height = "",
                weight = "",
                bmi = 0.0f,
                errorReasonHeight = ErrorReason.EMPTY,
                errorReasonWeight = ErrorReason.EMPTY
            )
        }
    }

    private fun isInvalidHeight(height: String): ErrorReason {
        val floatHeight = height.toFloatOrNull()
        return when {
            height.isEmpty() -> ErrorReason.EMPTY
            floatHeight == null -> ErrorReason.NOT_NUMBER
            floatHeight > MAX_HEIGHT -> ErrorReason.TOO_LARGE
            floatHeight < MIN_HEIGHT -> ErrorReason.TOO_SMALL
            else -> ErrorReason.NONE
        }
    }

    private fun isInvalidWeight(weight: String): ErrorReason {
        val floatWeight = weight.toFloatOrNull()
        return when {
            weight.isEmpty() -> ErrorReason.EMPTY
            floatWeight == null -> ErrorReason.NOT_NUMBER
            floatWeight > MAX_WEIGHT -> ErrorReason.TOO_LARGE
            floatWeight < MIN_WEIGHT -> ErrorReason.TOO_SMALL
            else -> ErrorReason.NONE
        }
    }
}