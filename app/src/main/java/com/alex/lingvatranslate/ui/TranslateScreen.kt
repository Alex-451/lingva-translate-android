package com.alex.lingvatranslate.ui

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(text = "Bababoey")
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                    translateUiState.languages.forEachIndexed { index, language ->
                        DropdownMenuItem(
                            onClick = { /*TODO*/ }) {
                            Text(text = language.name)
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f))
                {
                    TranslationBox(
                        isMain = true,
                        onTextChanged = { translateViewModel.updateTextToTranslate(it) },
                        text = translateViewModel.textToTranslate
                    )
                }

                Box(modifier = Modifier.weight(1f))
                {
                    TranslationBox(
                        isMain = false,
                        onTextChanged = {},
                        text = translateUiState.translation
                    )
                }

                Button(onClick = { translateViewModel.translateText() }) {
                    Text(text = "Translate")
                }
            }
        }
    }
}

@Composable
fun TranslationBox(
    isMain: Boolean = false,
    text: String,
    onTextChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = text, onValueChange = onTextChanged,
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 10.dp)
    )
}