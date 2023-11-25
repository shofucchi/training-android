package io.github.shofucchi.bmi.ui.test

import io.github.shofucchi.bmi.ui.BmiViewModel
import io.github.shofucchi.bmi.ui.ErrorReason
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class BmiViewModelTest {
    private lateinit var viewModel: BmiViewModel

    @Before
    fun setUp() {
        viewModel = BmiViewModel()
    }

    @Test
    fun bmiViewModel_enterMaxValidHeight() {
        viewModel.enterHeight("300.0")
        assertThat(viewModel.uiState.value.height).isEqualTo("300.0")
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterMinValidHeight() {
        viewModel.enterHeight("1.0")
        assertThat(viewModel.uiState.value.height).isEqualTo("1.0")
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterEmptyHeight() {
        viewModel.enterHeight("")
        assertThat(viewModel.uiState.value.height).isEqualTo("")
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.EMPTY)
    }

    @Test
    fun bmiViewModel_enterNotNumberHeight() {
        viewModel.enterHeight("abc")
        assertThat(viewModel.uiState.value.height).isEqualTo("abc")
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.NOT_NUMBER)
    }

    @Test
    fun bmiViewModel_enterTooLargeHeight() {
        viewModel.enterHeight("300.1")
        assertThat(viewModel.uiState.value.height).isEqualTo("300.1")
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.TOO_LARGE)
    }

    @Test
    fun bmiViewModel_enterTooSmallHeight() {
        viewModel.enterHeight("0.9")
        assertThat(viewModel.uiState.value.height).isEqualTo("0.9")
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.TOO_SMALL)
    }

    @Test
    fun bmiViewModel_enterMaxValidWeight() {
        viewModel.enterWeight("600.0")
        assertThat(viewModel.uiState.value.weight).isEqualTo("600.0")
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterMinValidWeight() {
        viewModel.enterWeight("0.1")
        assertThat(viewModel.uiState.value.weight).isEqualTo("0.1")
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterEmptyWeight() {
        viewModel.enterWeight("")
        assertThat(viewModel.uiState.value.weight).isEqualTo("")
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.EMPTY)
    }

    @Test
    fun bmiViewModel_enterNotNumberWeight() {
        viewModel.enterWeight("a,")
        assertThat(viewModel.uiState.value.weight).isEqualTo("a,")
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.NOT_NUMBER)
    }

    @Test
    fun bmiViewModel_enterTooLargeWeight() {
        viewModel.enterWeight("600.1")
        assertThat(viewModel.uiState.value.weight).isEqualTo("600.1")
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.TOO_LARGE)
    }

    @Test
    fun bmiViewModel_enterTooSmallWeight() {
        viewModel.enterWeight("0.09")
        assertThat(viewModel.uiState.value.weight).isEqualTo("0.09")
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.TOO_SMALL)
    }

    @Test
    fun bmiViewModel_calculateBmi() {
        viewModel.enterHeight("300.0")
        viewModel.enterWeight("600.0")
        viewModel.calculateBmi()
        assertThat(viewModel.uiState.value.bmi).isEqualTo(66.666664f)
    }

    @Test
    fun bmiViewMode_reset() {
        viewModel.enterHeight("300.0")
        viewModel.enterWeight("600.0")
        viewModel.reset()
        assertThat(viewModel.uiState.value.height).isEqualTo("")
        assertThat(viewModel.uiState.value.weight).isEqualTo("")
        assertThat(viewModel.uiState.value.bmi).isEqualTo(0.0f)
        assertThat(viewModel.uiState.value.errorReasonHeight).isEqualTo(ErrorReason.EMPTY)
        assertThat(viewModel.uiState.value.errorReasonWeight).isEqualTo(ErrorReason.EMPTY)
    }
}