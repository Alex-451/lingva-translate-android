package com.alex.lingvatranslate.di

import com.alex.lingvatranslate.data.network.ApolloLingvaClient
import com.alex.lingvatranslate.domain.LingvaClient
import com.alex.lingvatranslate.domain.useCases.GetLanguagesUseCase
import com.alex.lingvatranslate.domain.useCases.GetTranslationUseCase
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://lingva.ml/api/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideLingvaClient(apolloClient: ApolloClient): LingvaClient {
        return ApolloLingvaClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetLanguagesUseCase(lingvaClient: ApolloLingvaClient): GetLanguagesUseCase {
        return GetLanguagesUseCase(lingvaClient)
    }

    @Provides
    @Singleton
    fun provideGetTranslationUseCase(lingvaClient: ApolloLingvaClient): GetTranslationUseCase {
        return GetTranslationUseCase(lingvaClient)
    }

}