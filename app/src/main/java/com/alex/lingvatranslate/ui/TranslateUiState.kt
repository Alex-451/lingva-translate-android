package com.alex.lingvatranslate.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.alex.lingvatranslate.model.Language


data class TranslateUiState(
    val translation: String = "",
    val languages: List<Language> = mutableListOf(),
)