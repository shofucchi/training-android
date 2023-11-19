package io.github.shofucchi.bmi.ui

interface HomeUiState {
    data class Success(
        val height: String = "",
        val weight: String = "",
        val bmi: Float = 0.0f,
    ) : HomeUiState

    data class Error(
        val height: String = "",
        val weight: String = "",
        val bmi: Float = 0.0f,
        val error: String
    ) : HomeUiState
}
