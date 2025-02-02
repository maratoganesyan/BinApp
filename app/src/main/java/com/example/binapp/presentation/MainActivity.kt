package com.example.binapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.binapp.presentation.screen.NavController
import com.example.binapp.presentation.theme.BinAppTheme
import com.example.binapp.presentation.viewmodel.BinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: BinViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BinAppTheme {
                NavController(viewModel)
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    private fun UiPreview(){
        BinAppTheme {
            NavController(viewModel)
        }
    }

}