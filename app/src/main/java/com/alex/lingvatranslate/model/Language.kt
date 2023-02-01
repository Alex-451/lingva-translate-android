package com.alex.lingvatranslate.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val code: String,
    val name: String
)