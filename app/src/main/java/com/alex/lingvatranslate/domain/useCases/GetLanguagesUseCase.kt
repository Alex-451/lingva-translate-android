package com.alex.lingvatranslate.domain.useCases

import com.alex.lingvatranslate.domain.LingvaClient
import com.alex.lingvatranslate.domain.models.Language

class GetLanguagesUseCase(private val lingvaClient: LingvaClient) {
    suspend fun execute(): List<Language> {
        return lingvaClient.getLanguages()
    }
}