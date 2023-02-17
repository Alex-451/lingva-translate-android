package com.alex.lingvatranslate.ui.translate

import com.alex.lingvatranslate.model.Language

data class TranslateState(
    val languages: List<Language> = emptyList()
)
