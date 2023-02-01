package com.alex.lingvatranslate.model

import kotlinx.serialization.Serializable

@Serializable
data class Languages (
    val languages: List<Language>
    )