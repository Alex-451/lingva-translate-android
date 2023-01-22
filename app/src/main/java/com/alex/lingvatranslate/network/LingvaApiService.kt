package com.alex.lingvatranslate.network

import com.alex.lingvatranslate.model.Translation
import retrofit2.http.GET
import retrofit2.http.Path

interface LingvaApiService {
    @GET("{source}/{target}/{query}")
    suspend fun getTranslation(@Path("source") source: String, @Path("target") target: String, @Path("query") query: String): Translation
}