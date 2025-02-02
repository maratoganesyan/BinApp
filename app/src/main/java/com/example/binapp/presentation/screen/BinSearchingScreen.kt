package com.example.binapp.presentation.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.binapp.presentation.viewmodel.BinViewModel
import com.example.binapp.tools.historyNav

@Composable
fun BinSearchingScreen(
    viewModel: BinViewModel,
    navController: NavController
) {
    val binInfo by viewModel.binInfo.collectAsState()
    val error by viewModel.error.collectAsState()
    var binInput by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Поле для ввода BIN
        OutlinedTextField(
            value = binInput,
            onValueChange = { binInput = it; viewModel.OnSearchingChange()},
            label = { Text("Введите BIN") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для поиска
        Button(
            onClick = { viewModel.getBinInfo(binInput) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поиск")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Отображение информации о BIN
        binInfo?.let { info ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Страна: ${info.country.name}")
                    ClickableText(
                        AnnotatedString("Координаты страны: ${info.country.latitude}, ${info.country.longitude}"),
                            onClick = { viewModel.run {
                                openCountryInMap(info.country.latitude, info.country.longitude,
                                    context)
                            } })
                    Text("Тип карты: ${info.scheme}")
                    Text("Банк: ${info.bank.name}")
                    Text("Телефон банка: ${info.bank.phone}")
                    Text("Город банка: ${info.bank.city}")
                    Text("Сайт банка: ${info.bank.url}")
                }
            }
        }

        // Отображение ошибки
        error?.let {
            Text(
                text = it,
                color = colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для перехода к истории
        Button(
            onClick = { navController.navigate(historyNav) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("История запросов")
        }
    }
}



