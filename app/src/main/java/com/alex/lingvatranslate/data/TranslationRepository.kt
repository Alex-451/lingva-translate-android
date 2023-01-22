package com.alex.lingvatranslate.data

import com.alex.lingvatranslate.model.Translation
import com.alex.lingvatranslate.network.LingvaApiService

interface TranslationRepository {
    suspend fun getTranslation(source: String, target: String, query: String): Translation
}

class DefaultTranslationRepository(private val lingvaApiService: LingvaApiService) :
    TranslationRepository {

    override suspend fun getTranslation(
        source: String,
        target: String,
        query: String
    ): Translation {
        return lingvaApiService.getTranslation(source, target, query)
    }
}