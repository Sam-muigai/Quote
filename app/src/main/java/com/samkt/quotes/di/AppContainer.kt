package com.samkt.quotes.di

import com.samkt.quotes.data.QuotesApi
import com.samkt.quotes.data.QuotesRepositoryImpl
import com.samkt.quotes.domain.QuoteRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object AppContainer {
    private fun quoteApi(): QuotesApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(QuotesApi.BASE_URL)
            .build()
            .create(QuotesApi::class.java)
    }

    fun quotesRepository(): QuoteRepository {
        return QuotesRepositoryImpl(quoteApi())
    }
}
