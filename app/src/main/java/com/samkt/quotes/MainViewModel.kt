package com.samkt.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samkt.quotes.model.QuotesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _mainScreenState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
    val mainScreenState = _mainScreenState.asStateFlow()

    private val quotesApi = QuotesApi.quoteApi()

    init {
        getQuote()
    }

    private fun getQuote() {
        _mainScreenState.value = MainScreenState.Loading
        viewModelScope.launch {
            try {
                val quote = quotesApi.getQuote()
                _mainScreenState.value = MainScreenState.Success(quote)
            } catch (e: Exception) {
                _mainScreenState.value =
                    MainScreenState.Error(e.localizedMessage ?: "Unknown error occured!!")
            }
        }
    }
}

sealed interface MainScreenState {
    data class Success(val quote: QuotesResponse) : MainScreenState

    data class Error(val message: String) : MainScreenState

    data object Loading : MainScreenState
}
