package io.github.shofucchi.bmi.ui

data class BmiUiState(
    val height: String = "",
    val weight: String = "",
    val bmi: Float = 0.0f,
    val errorReasonHeight: ErrorReason = ErrorReason.EMPTY,
    val errorReasonWeight: ErrorReason = ErrorReason.EMPTY
)

enum class ErrorReason {
    NONE,
    EMPTY,
    NOT_NUMBER,
    TOO_LARGE,
    TOO_SMALL
}

const val MAX_HEIGHT = 300.0f
const val MIN_HEIGHT = 1.0f

const val MAX_WEIGHT = 600.0f
const val MIN_WEIGHT = 0.1f
