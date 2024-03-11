package com.samkt.quotes.domain

import com.samkt.quotes.domain.model.Quotes
import com.samkt.quotes.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getQuote(): Flow<ApiResult<Quotes>>
}
