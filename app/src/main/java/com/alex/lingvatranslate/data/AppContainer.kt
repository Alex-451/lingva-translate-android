package com.alex.lingvatranslate.data

import com.alex.lingvatranslate.network.LingvaApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

interface AppContainer {
    val translationRepository: TranslationRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://lingva.ml/api/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: LingvaApiService by lazy {
        retrofit.create(LingvaApiService::class.java)
    }

    override val translationRepository: TranslationRepository by lazy {
        DefaultTranslationRepository(retrofitService)
    }
}