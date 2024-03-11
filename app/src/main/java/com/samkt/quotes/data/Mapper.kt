package com.samkt.quotes.data

import com.samkt.quotes.data.dtos.QuotesResponse
import com.samkt.quotes.domain.model.Quotes

fun QuotesResponse.toQuotes(): Quotes {
    return Quotes(content = content)
}
