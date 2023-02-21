package com.alex.lingvatranslate.ui.languageSelection

import com.alex.lingvatranslate.domain.models.Language

data class LanguageSelectionState(
    val languages: List<Language> = emptyList(),
)