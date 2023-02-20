package com.alex.lingvatranslate.data.apolloMappers

import com.alex.lingvatranslate.TranslateQuery
import com.alex.lingvatranslate.domain.models.Translation

fun TranslateQuery.Translation.toTranslation() : Translation {
    return Translation(
        translation = target.text,
    )
}