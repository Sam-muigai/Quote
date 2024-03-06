package com.samkt.quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.samkt.quotes.model.QuotesResponse
import com.samkt.quotes.ui.theme.QuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
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
    quote: QuotesResponse,
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
