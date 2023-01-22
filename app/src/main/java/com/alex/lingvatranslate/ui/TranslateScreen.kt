package com.alex.lingvatranslate.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview
@Composable
fun TranslateScreen(
    modifier: Modifier = Modifier,
    translateViewModel: TranslateViewModel = viewModel(factory = TranslateViewModel.Factory)
) {
    val translateUiState by translateViewModel.uiState.collectAsState()

    TranslationBox(isMain = true, onTextChanged = {translateViewModel.updateTextToTranslate(it)}, text = translateViewModel.textToTranslate, onButtonClick = {translateViewModel.translateText()}, result = translateUiState.translation)
}

@Composable
fun TranslationBox(
    isMain: Boolean = false,
    text: String,
    onTextChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    result: String
) {
    Column() {
        OutlinedTextField(value = text, onValueChange = onTextChanged)
        Button(onClick = onButtonClick) {
            Text(text = "Translate")
        }
        Text(text = result)
    }
}