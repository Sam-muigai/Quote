package com.samkt.quotes

import com.samkt.quotes.model.QuotesResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface QuotesApi {
    @GET("random")
    suspend fun getQuote(): QuotesResponse

    companion object {
        private const val BASE_URL = "https://api.quotable.io/"

        fun quoteApi(): QuotesApi {
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(QuotesApi::class.java)
        }
    }
}
