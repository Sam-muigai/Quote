package com.samkt.quotes.data

import com.samkt.quotes.domain.QuoteRepository
import com.samkt.quotes.domain.model.Quotes
import com.samkt.quotes.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuotesRepositoryImpl(
    private val quotesApi: QuotesApi,
) : QuoteRepository {
    override fun getQuote(): Flow<ApiResult<Quotes>> =
        flow {
            try {
                val quote = quotesApi.getQuote()
                emit(ApiResult.Success(quote.toQuotes()))
            } catch (e: Exception) {
                emit(ApiResult.Error(e.localizedMessage))
            }
        }
}
