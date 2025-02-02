package com.example.binapp.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.model.BinInfoModel
import com.example.binapp.domain.usecase.DeleteBinHistoryUseCase
import com.example.binapp.domain.usecase.GetBinHistoryUseCase
import com.example.binapp.domain.usecase.GetBinInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BinViewModel(
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val getHistoryUseCase: GetBinHistoryUseCase,
    private val deleteHistoryUseCase: DeleteBinHistoryUseCase
) : ViewModel() {

    private val _binInfo = MutableStateFlow<BinInfo?>(null)
    val binInfo: StateFlow<BinInfo?> = _binInfo

    private val _history = MutableStateFlow<List<BinInfoModel>>(emptyList())
    val history: StateFlow<List<BinInfoModel>> = _history


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getBinInfo(bin: String) {
        viewModelScope.launch {
            try {
                _binInfo.value = getBinInfoUseCase(bin)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            }
        }
    }

    fun OnSearchingChange(){
        _error.value = null
    }

    // Загрузка истории запросов
    fun getHistory() {
        viewModelScope.launch {
            try {
                getHistoryUseCase().collect { history ->
                    _history.value = history
                }
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки истории: ${e.message}"
            }
        }
    }

    fun deleteHistory(){
        viewModelScope.launch {
            try {
                deleteHistoryUseCase()
            }catch (e : Exception){
                _error.value = "Ошибка удаления данных: ${e.message}"
            }
        }
    }

    fun openCountryInMap(latitude: Double, longitude: Double, context : Context){
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(context, mapIntent, null)
    }

}