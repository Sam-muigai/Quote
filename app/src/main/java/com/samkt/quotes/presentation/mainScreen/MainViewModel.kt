package com.samkt.quotes.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.quotes.domain.QuoteRepository
import com.samkt.quotes.domain.model.Quotes
import com.samkt.quotes.util.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val quoteRepository: QuoteRepository,
) : ViewModel() {
    private val _mainScreenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val mainScreenState = _mainScreenState.asStateFlow()

    init {
        getQuote()
    }

    private fun getQuote() {
        _mainScreenState.value = MainScreenState.Loading
        quoteRepository.getQuote().onEach { apiResult ->
            when (apiResult) {
                is ApiResult.Error -> {
                    _mainScreenState.value = MainScreenState.Error(apiResult.message)
                }

                is ApiResult.Success -> {
                    apiResult.data?.let { quote ->
                        _mainScreenState.value = MainScreenState.Success(quote)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

sealed interface MainScreenState {
    data class Success(val quote: Quotes) : MainScreenState

    data class Error(val message: String?) : MainScreenState

    data object Loading : MainScreenState
}
