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
        val errorReason = isInvalidHeight(height.toFloatOrNull())
        if (errorReason != ErrorReason.NONE) {
            _uiState.update { it.copy(height = height, errorReasonHeight = errorReason) }
        } else {
            _uiState.update { it.copy(height = height, errorReasonHeight = ErrorReason.NONE) }
        }
    }

    fun enterWeight(weight: String) {
        val errorReason = isInvalidWeight(weight.toFloatOrNull())
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
                errorReasonHeight = ErrorReason.NONE,
                errorReasonWeight = ErrorReason.NONE
            )
        }
    }

    private fun isInvalidHeight(height: Float?): ErrorReason {
        return when {
            height == null -> ErrorReason.NOT_NUMBER
            height > MAX_HEIGHT -> ErrorReason.TOO_LARGE
            height < MIN_HEIGHT -> ErrorReason.TOO_SMALL
            else -> ErrorReason.NONE
        }
    }

    private fun isInvalidWeight(weight: Float?): ErrorReason {
        return when {
            weight == null -> ErrorReason.NOT_NUMBER
            weight > MAX_WEIGHT -> ErrorReason.TOO_LARGE
            weight < MIN_WEIGHT -> ErrorReason.TOO_SMALL
            else -> ErrorReason.NONE
        }
    }
}