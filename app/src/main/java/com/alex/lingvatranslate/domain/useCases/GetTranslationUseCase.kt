package com.alex.lingvatranslate.domain.useCases

import com.alex.lingvatranslate.data.network.ApolloLingvaClient
import com.alex.lingvatranslate.domain.models.Translation

class GetTranslationUseCase(private val lingvaClient: ApolloLingvaClient) {
    suspend fun execute(sourceLanguage: String, targetLanguage: String, query: String): Translation {
        return lingvaClient.getTranslation(sourceLanguage, targetLanguage, query)
    }
}