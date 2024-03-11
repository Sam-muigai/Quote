package com.samkt.quotes.data

import com.samkt.quotes.data.dtos.QuotesResponse
import retrofit2.http.GET

interface QuotesApi {
    @GET("random")
    suspend fun getQuote(): QuotesResponse

    companion object {
        const val BASE_URL = "https://api.quotable.io/"
    }
}
