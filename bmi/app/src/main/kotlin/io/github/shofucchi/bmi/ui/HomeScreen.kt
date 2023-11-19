package io.github.shofucchi.bmi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import io.github.shofucchi.bmi.R
import io.github.shofucchi.bmi.ui.theme.BmiTheme

@Composable
fun HomeScreen() {
    val containerPadding = dimensionResource(R.dimen.container_padding)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(containerPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.your_bmi, 0),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Start)
                .padding(containerPadding),
            style = typography.displaySmall
        )
        BmiCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(containerPadding)
        )
        OutlinedButton(
            modifier = Modifier
                .fillMaxSize()
                .padding(containerPadding),
            onClick = { /*TODO*/ }) {
            Text(text = "Reset")
        }
    }
}

@Composable
fun BmiCard(modifier: Modifier = Modifier) {
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
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text(text = "Enter your height") }
            )
            BmiOutlinedTextField(
                value = "",
                onValueChange = { /*TODO*/ },
                label = { Text(text = "Enter your weight") }
            )
        }
    }
}

@Composable
fun BmiOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = Modifier.fillMaxSize(),
        label = label,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorScheme.surface,
            unfocusedContainerColor = colorScheme.surface,
            disabledContainerColor = colorScheme.surface,
        ),
        shape = shapes.large
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BmiTheme {
        HomeScreen()
    }
}