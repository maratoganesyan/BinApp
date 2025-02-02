package com.example.binapp.presentation.screen

import android.service.autofill.OnClickAction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.binapp.presentation.viewmodel.BinViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.example.binapp.tools.searchingNav

@Composable
fun BinHistoryScreen(
    viewModel: BinViewModel,
    navController: NavController
) {
    val history by viewModel.history.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getHistory()
    }

   Column(Modifier.fillMaxSize().padding(16.dp, 30.dp, 16.dp, 16.dp)) {
       Button(
           onClick = { navController.navigate(searchingNav) },
           modifier = Modifier.fillMaxWidth().padding(16.dp)
       ) {
           Text("Поиск Bin")
       }

       Button(
           onClick = { viewModel.deleteHistory() },
           modifier = Modifier.fillMaxWidth().padding(16.dp)
       ) {
           Text("Очистить историю")
       }
       LazyColumn(
           modifier = Modifier.padding(16.dp),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {

           items(history){ binInfo ->
               Card(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(vertical = 8.dp)
               ) {
                   Column(
                       modifier = Modifier.padding(16.dp)
                   ) {
                       Text("BIN: ${binInfo.bin}")
                       Text("Страна: ${binInfo.countryName}")
                       ClickableText(
                           AnnotatedString("Координаты страны: ${binInfo.countryLatitude}, ${binInfo.countryLongitude}"),
                           onClick = { viewModel.run {
                               openCountryInMap(binInfo.countryLatitude, binInfo.countryLongitude,
                                   context)
                           } })
                       Text("Банк: ${binInfo.bankName}")
                       Text("Телефон: ${binInfo.bankPhone}")
                       Text("Город: ${binInfo.countryName}")
                   }
               }
           }
       }
   }
}