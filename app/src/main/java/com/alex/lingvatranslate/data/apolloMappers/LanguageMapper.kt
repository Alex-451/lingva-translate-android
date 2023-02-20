package com.alex.lingvatranslate.data.apolloMappers

import com.alex.lingvatranslate.LanguagesQuery
import com.alex.lingvatranslate.domain.models.Language

fun LanguagesQuery.Language.toLanguage() : Language {
    return Language(
        code = code,
        name = name
    )
}