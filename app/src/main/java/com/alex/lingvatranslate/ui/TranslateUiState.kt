package com.alex.lingvatranslate.ui

import com.alex.lingvatranslate.model.Language


data class TranslateUiState(
    val translation: String = "",
    val languages: List<Language> = mutableListOf()
)