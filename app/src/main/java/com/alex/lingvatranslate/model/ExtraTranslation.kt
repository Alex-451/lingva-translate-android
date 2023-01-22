package com.alex.lingvatranslate.model

import kotlinx.serialization.Serializable

@Serializable
data class ExtraTranslation(
    val type: String = "",
    val list: List<ExtraTranslationList>
)

@Serializable
data class ExtraTranslationList(
    val word: String = "",
    val meanings: List<String>,
    val frequency: Int
)
