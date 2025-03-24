package com.example.jpc_address_validation_demo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jpc_address_validation_demo.ui.theme.JpcaddressvalidationdemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JpcaddressvalidationdemoTheme {
                AddressApp()
            }
        }
    }
}

@Composable
fun AddressApp() {
    val mainViewModel: MainViewModel = viewModel()
    val result = mainViewModel.result.collectAsState()
    var valueAddress = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            OutlinedTextField(
                modifier = Modifier.padding(horizontal = 24.dp),
                value = valueAddress.value,
                onValueChange = {
                    valueAddress.value = it
                },
                singleLine = true,
                //textStyle = TextStyle(brush = brush),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                // 2. Handle the action key press.
                keyboardActions = KeyboardActions(
                    onSearch = {
                        mainViewModel.validateAddress(valueAddress.value)
                        keyboardController?.hide()
                    }
                ),
                leadingIcon = {
                    Icon(Icons.Default.Search, Icons.Default.Search.name)
                },
                trailingIcon = {
                    IconButton(
                        onClick = { valueAddress.value = "" },
                        content = {
                            if (valueAddress.value.isNotEmpty()) {
                                Icon(Icons.Default.Close, Icons.Default.Close.name)
                            }
                        }
                    )
                },
                label = { Text("Suche…") },
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(result.value ?: "Address not found or error" )
        }
    )
    Log.d("DEBUG", result.value ?: "Address not found or error")
}
