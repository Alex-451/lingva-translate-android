package com.alex.lingvatranslate.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.lingvatranslate.ui.viewModel.TranslateViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateScreen(
    modifier: Modifier = Modifier,
    translateViewModel: TranslateViewModel = viewModel(factory = TranslateViewModel.Factory)
) {
    val translateUiState by translateViewModel.uiState.collectAsState()
    var isSourceLanguageExpanded by remember { mutableStateOf(false) }

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
                        ExposedDropdownMenuBox(
                            expanded = isSourceLanguageExpanded,
                            onExpandedChange = {
                                isSourceLanguageExpanded = !isSourceLanguageExpanded
                            }) {
                            OutlinedTextField(
                                value = "Deutsch",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isSourceLanguageExpanded)
                                })
                        }
                    }
                    Spacer(
                        Modifier
                            .padding(5.dp)
                    )

                    Box(modifier = Modifier.weight(1f)) {
                        ExposedDropdownMenuBox(
                            expanded = isSourceLanguageExpanded,
                            onExpandedChange = {
                                isSourceLanguageExpanded = !isSourceLanguageExpanded
                            }) {
                            OutlinedTextField(
                                value = "Englisch",
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isSourceLanguageExpanded)
                                })
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

@OptIn(ExperimentalMaterial3Api::class)
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