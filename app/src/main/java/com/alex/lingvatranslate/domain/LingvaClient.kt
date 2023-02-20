package com.alex.lingvatranslate.domain

import com.alex.lingvatranslate.LanguagesQuery
import com.alex.lingvatranslate.domain.models.Language
import com.alex.lingvatranslate.domain.models.Translation

interface LingvaClient {
    suspend fun getLanguages(): List<Language>
    suspend fun getTranslation(sourceLanguage: String, targetLanguage: String, query: String): Translation
}