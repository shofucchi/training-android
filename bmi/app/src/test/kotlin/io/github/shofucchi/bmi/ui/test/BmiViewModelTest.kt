package io.github.shofucchi.bmi.ui.test

import io.github.shofucchi.bmi.ui.BmiViewModel
import io.github.shofucchi.bmi.ui.ErrorReason
import org.junit.Before
import org.junit.Test

class BmiViewModelTest {
    private lateinit var viewModel: BmiViewModel

    @Before
    fun setUp() {
        viewModel = BmiViewModel()
    }

    @Test
    fun bmiViewModel_enterValidHeight_BoundLarge() {
        viewModel.enterHeight("300.0")
        assert(viewModel.uiState.value.height == "300.0")
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterValidHeight_BoundSmal() {
        viewModel.enterHeight("1.0")
        assert(viewModel.uiState.value.height == "1.0")
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterEmptyHeight() {
        viewModel.enterHeight("")
        assert(viewModel.uiState.value.height == "")
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.EMPTY)
    }

    @Test
    fun bmiViewModel_enterNotNumberHeight() {
        viewModel.enterHeight("abc")
        assert(viewModel.uiState.value.height == "abc")
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.NOT_NUMBER)
    }

    @Test
    fun bmiViewModel_enterTooLargeHeight() {
        viewModel.enterHeight("300.1")
        assert(viewModel.uiState.value.height == "300.1")
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.TOO_LARGE)
    }

    @Test
    fun bmiViewModel_enterTooSmallHeight() {
        viewModel.enterHeight("0.9")
        assert(viewModel.uiState.value.height == "0.9")
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.TOO_SMALL)
    }

    @Test
    fun bmiViewModel_enterValidWeight_BoundLarge() {
        viewModel.enterWeight("600.0")
        assert(viewModel.uiState.value.weight == "600.0")
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterValidWeight_BoundSmall() {
        viewModel.enterWeight("0.1")
        assert(viewModel.uiState.value.weight == "0.1")
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.NONE)
    }

    @Test
    fun bmiViewModel_enterEmptyWeight() {
        viewModel.enterWeight("")
        assert(viewModel.uiState.value.weight == "")
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.EMPTY)
    }

    @Test
    fun bmiViewModel_enterNotNumberWeight() {
        viewModel.enterWeight("a,")
        assert(viewModel.uiState.value.weight == "a,")
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.NOT_NUMBER)
    }

    @Test
    fun bmiViewModel_enterTooLargeWeight() {
        viewModel.enterWeight("600.1")
        assert(viewModel.uiState.value.weight == "600.1")
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.TOO_LARGE)
    }

    @Test
    fun bmiViewModel_enterTooSmallWeight() {
        viewModel.enterWeight("0.09")
        assert(viewModel.uiState.value.weight == "0.09")
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.TOO_SMALL)
    }

    @Test
    fun bmiViewModel_calculateBmi() {
        viewModel.enterHeight("300.0")
        viewModel.enterWeight("600.0")
        viewModel.calculateBmi()
        assert(viewModel.uiState.value.bmi == 66.666664f)
    }

    @Test
    fun bmiViewMode_reset() {
        viewModel.enterHeight("300.0")
        viewModel.enterWeight("600.0")
        viewModel.reset()
        assert(viewModel.uiState.value.height == "")
        assert(viewModel.uiState.value.weight == "")
        assert(viewModel.uiState.value.bmi == 0.0f)
        assert(viewModel.uiState.value.errorReasonHeight == ErrorReason.EMPTY)
        assert(viewModel.uiState.value.errorReasonWeight == ErrorReason.EMPTY)
    }
}