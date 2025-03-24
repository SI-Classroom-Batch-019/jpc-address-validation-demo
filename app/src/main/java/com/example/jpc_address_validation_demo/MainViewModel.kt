package com.example.jpc_address_validation_demo

import android.util.Log
import androidx.compose.material3.ModalBottomSheetDefaults.properties
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpc_address_validation_demo.data.remote.KomootApi
import com.example.jpc_address_validation_demo.data.remote.OpenStreetApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _resultOpenStreet = MutableStateFlow<String?>(null)
    val resultOpenStreet = _resultOpenStreet.asStateFlow()

    private val _resultKomoot = MutableStateFlow<String?>(null)
    val resultKomoot = _resultKomoot.asStateFlow()

    fun validateOpenStreetAddress(address: String) {
        viewModelScope.launch {
            try {
                val results = OpenStreetApi.service.searchAddress(address)
                if (results.isNotEmpty()) {
                    _resultOpenStreet.value = "Valid: ${results[0].display_name}"
                } else {
                    _resultOpenStreet.value = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _resultOpenStreet.value = null
            }
        }
    }

    fun validateKomootAddress(address: String) {
        viewModelScope.launch {
            try {
                val result = KomootApi.service.searchAddress(address)
                val features = result.features
                if (features.isNotEmpty()) {
                    features.forEach {
                        Log.d("DEBUG", "feature.properties: ${it.properties}")
                    }
                    _resultKomoot.value = "Valid: ${features.first().properties}"
                } else {
                    _resultKomoot.value = null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _resultKomoot.value = null
            }
        }
    }
}