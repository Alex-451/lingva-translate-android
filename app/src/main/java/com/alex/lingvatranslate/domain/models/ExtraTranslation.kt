package com.alex.lingvatranslate.domain.models

data class ExtraTranslation(
    val type: String?,
    val list: List<AlternativeTranslation>?
)