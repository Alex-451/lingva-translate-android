package com.alex.lingvatranslate.ui.translate

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.lingvatranslate.model.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateView(
    viewModel: TranslateViewModel = viewModel()
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Lingva")
                            }
                            append("Translate")
                        })
                    },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Rounded.Star, contentDescription = null)
                        }
                    },

                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Rounded.MoreVert, contentDescription = null)
                        }
                    },
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Row(
                    Modifier
                        .padding(padding),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        LanguageDropDown(languages = viewState.languages, isSource = true, onSelectionChanged = {viewModel.updateSelectedSourceLanguage(it)})
                    }

                    Spacer(
                        Modifier
                            .padding(5.dp)
                    )

                    Box(modifier = Modifier.weight(1f)) {
                        LanguageDropDown(languages = viewState.languages, isSource = false, onSelectionChanged = {viewModel.updateSelectedTargetLanguage(it)})
                    }
                }

                Box(modifier = Modifier.weight(1f))
                {
                    TranslationBox(
                        isSource = true,
                        onTextToTranslateChanged = {  viewModel.updateTextToTranslate(it)},
                        textToTranslate = viewState.textToTranslate
                    )
                }
                
                Button(onClick = { viewModel.translateText() }) {
                    Text(text = "Translate")
                }

                Box(modifier = Modifier.weight(1f))
                {
                    TranslationBox(
                        isSource = false,
                        onTextToTranslateChanged = {},
                        textToTranslate = viewState.translationResult.translation
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropDown(languages: List<Language>, isSource: Boolean = false, onSelectionChanged: (Language) -> Unit) {
    var filteredLangs: List<Language>

    if (!isSource)
        filteredLangs = languages.filter { it.name != "Detect" }
    else
        filteredLangs = languages

    var isExpanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember {
        if (isSource)
            mutableStateOf(Language("auto", "Detect"))
        else
            mutableStateOf(Language("af", "Afrikaans"))
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedLanguage.name,
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.menuAnchor()

        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            filteredLangs.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption.name) },
                    onClick = {
                        selectedLanguage = selectionOption
                        isExpanded = false
                        onSelectionChanged(selectedLanguage)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationBox(
    isSource: Boolean = false,
    textToTranslate: String,
    onTextToTranslateChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = textToTranslate, onValueChange = onTextToTranslateChanged,
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(0.dp, 10.dp)
    )
}
