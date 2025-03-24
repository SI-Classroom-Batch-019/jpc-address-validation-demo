package com.example.jpc_address_validation_demo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _result = MutableStateFlow<String?>(null)
    val result = _result.asStateFlow()

    fun validateAddress(address: String) {
        viewModelScope.launch {
            try {
                val results = Api.service.searchAddress(address)
                if (results.isNotEmpty()) {
                    Log.d("DEBUG", "results: $results")
                    _result.value = "Valid: ${results[0].display_name}"
                } else {
                    _result.value = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _result.value = null
            }
        }
    }
}