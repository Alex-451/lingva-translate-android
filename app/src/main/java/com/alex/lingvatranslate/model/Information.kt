package com.alex.lingvatranslate.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Information(
    val pronunciation: Map<String, String>,
    val definitions: Array<String>,
    val examples: Array<String>,
    val similar: Array<String>,
    val extraTranslations: List<ExtraTranslation>,
)
