package com.alex.lingvatranslate.ui.translate

import com.alex.lingvatranslate.model.Language
import com.alex.lingvatranslate.model.Languages
import com.alex.lingvatranslate.model.Translation

data class TranslateState(
    val languages: List<Language> = emptyList(),
    val translationResult: Translation = Translation("", null),
    val textToTranslate: String = "",
    val selectedSourceLanguage: Language = Language("",""),
    val selectedTargetLanguage: Language = Language("","")
)
