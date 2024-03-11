package com.samkt.quotes.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samkt.quotes.di.AppContainer
import com.samkt.quotes.domain.model.Quotes
import com.samkt.quotes.util.viewModelFactory

@Composable
fun MainScreen(
    mainViewModel: MainViewModel =
        viewModel(
            factory =
                viewModelFactory {
                    MainViewModel(AppContainer.quotesRepository())
                },
        ),
) {
    when (val state = mainViewModel.mainScreenState.collectAsState().value) {
        is MainScreenState.Error -> {
        }

        is MainScreenState.Loading -> {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        is MainScreenState.Success -> {
            MainScreenContent(quote = state.quote)
        }
    }
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    quote: Quotes,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            Text(text = quote.content)
        }
    }
}
