package com.alex.lingvatranslate.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translation(
    @SerialName(value = "translation")
    val translation: String,
    val info: Information?
)