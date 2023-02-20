package com.alex.lingvatranslate.ui.translate

import com.alex.lingvatranslate.domain.models.Language
import com.alex.lingvatranslate.domain.models.Translation

data class TranslateState(
    val languages: List<Language> = emptyList(),
    val translationResult: Translation = Translation(""),
    val textToTranslate: String = "",
    val selectedSourceLanguage: Language = Language("",""),
    val selectedTargetLanguage: Language = Language("","")
)
