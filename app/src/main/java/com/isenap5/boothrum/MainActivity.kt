package com.isenap5.boothrum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.isenap5.boothrum.presentation.component.BooruViewModel
import com.isenap5.boothrum.ui.theme.BoothrumTheme


class MainActivity : ComponentActivity() {

    private val viewModel: BooruViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoothrumTheme {
                MainScreen(viewModel)
            }
        }
    }
}