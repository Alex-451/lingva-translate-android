package com.alex.lingvatranslate.data

import com.alex.lingvatranslate.network.LingvaApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val BASE_URL = "https://lingva.ml/api/v1/"

    @Provides
    @Singleton
    fun provideRetrofitService() : LingvaApiService {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .baseUrl(BASE_URL)
            .build()
            .create(LingvaApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTranslationRepository(apiService: LingvaApiService) : TranslationRepository {
        return DefaultTranslationRepository(apiService)
    }
}