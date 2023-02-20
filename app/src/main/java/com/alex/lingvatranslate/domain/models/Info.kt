package com.alex.lingvatranslate.domain.models

data class Info(
    val detectedSource: String?,
    val typo: String?,
    val pronunciation: Pronunciation?,
    val definitions: List<String>?,
    val examples: List<String>?,
    val similar: List<String>?,
    val extraTranslations: List<ExtraTranslation>?
)
