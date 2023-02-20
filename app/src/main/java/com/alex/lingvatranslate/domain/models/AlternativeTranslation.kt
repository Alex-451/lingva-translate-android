package com.alex.lingvatranslate.domain.models

data class AlternativeTranslation(
    val word: String?,
    val meanings: List<String>?,
    val frequency: Int
)