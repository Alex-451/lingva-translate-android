package com.alex.lingvatranslate.data.network

import com.alex.lingvatranslate.LanguagesQuery
import com.alex.lingvatranslate.TranslateQuery
import com.alex.lingvatranslate.data.apolloMappers.toLanguage
import com.alex.lingvatranslate.data.apolloMappers.toTranslation
import com.alex.lingvatranslate.domain.LingvaClient
import com.alex.lingvatranslate.domain.models.Language
import com.alex.lingvatranslate.domain.models.Translation
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class ApolloLingvaClient @Inject constructor(private val apolloClient: ApolloClient) : LingvaClient {

    override suspend fun getLanguages(): List<Language> {
        return apolloClient
            .query(LanguagesQuery())
            .execute()
            .data
            ?.languages
            ?.map { it!!.toLanguage() }
            ?: emptyList()
    }

    override suspend fun getTranslation(sourceLanguage: String, targetLanguage: String, query: String): Translation {
        return apolloClient
            .query(TranslateQuery(sourceLanguage, targetLanguage, query))
            .execute()
            .data
            ?.translation
            ?.toTranslation()!!
    }

}