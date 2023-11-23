package io.github.shofucchi.bmi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.shofucchi.bmi.R
import io.github.shofucchi.bmi.ui.theme.BmiTheme

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val containerPadding = dimensionResource(R.dimen.container_padding)
    val homeUiState = homeViewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(containerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.your_bmi, homeUiState.value.bmi),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Start)
                .padding(containerPadding),
            style = typography.displaySmall
        )
        BmiCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(containerPadding),
            homeViewModel = homeViewModel,
            homeUiState = homeUiState.value
        )
        OutlinedButton(
            modifier = Modifier
                .fillMaxSize()
                .padding(containerPadding),
            onClick = { homeViewModel.reset() }) {
            Text(text = "Reset")
        }
    }
}

@Composable
fun BmiCard(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    homeUiState: HomeUiState
) {
    val containerPadding = dimensionResource(R.dimen.container_padding)
    Card(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(containerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(containerPadding)
        ) {
            BmiOutlinedTextField(
                value = homeUiState.height,
                onValueChange = { homeViewModel.enterHeight(it) },
                onKeyboardDone = {
                    if (homeUiState.errorReasonHeight == ErrorReason.NONE && homeUiState.errorReasonWeight == ErrorReason.NONE) {
                        homeViewModel.calculateBmi()
                    }
                },
                label = {
                    val message = when (homeUiState.errorReasonHeight) {
                        ErrorReason.NOT_NUMBER -> stringResource(id = R.string.number_only)
                        ErrorReason.TOO_LARGE -> stringResource(id = R.string.too_large)
                        ErrorReason.TOO_SMALL -> stringResource(id = R.string.too_small)
                        else -> stringResource(id = R.string.height_placeholder)
                    }
                    Text(text = message)
                },
                isError = homeUiState.errorReasonHeight != ErrorReason.NONE
            )
            BmiOutlinedTextField(
                value = homeUiState.weight,
                onValueChange = { homeViewModel.enterWeight(it) },
                onKeyboardDone = {
                    if (homeUiState.errorReasonHeight == ErrorReason.NONE && homeUiState.errorReasonWeight == ErrorReason.NONE) {
                        homeViewModel.calculateBmi()
                    }
                },
                label = {
                    val message = when (homeUiState.errorReasonWeight) {
                        ErrorReason.NOT_NUMBER -> stringResource(id = R.string.number_only)
                        ErrorReason.TOO_LARGE -> stringResource(id = R.string.too_large)
                        ErrorReason.TOO_SMALL -> stringResource(id = R.string.too_small)
                        else -> stringResource(id = R.string.weight_placeholder)
                    }
                    Text(text = message)
                },
                isError = homeUiState.errorReasonWeight != ErrorReason.NONE
            )
        }
    }
}

@Composable
fun BmiOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    label: @Composable () -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier.fillMaxSize(),
        label = label,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(onDone = { onKeyboardDone() }),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.surface,
            disabledContainerColor = colorScheme.surface,
        ),
        shape = shapes.large,
        isError = isError
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BmiTheme {
        HomeScreen()
    }
}