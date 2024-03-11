package com.samkt.quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.samkt.quotes.presentation.mainScreen.MainScreen
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
