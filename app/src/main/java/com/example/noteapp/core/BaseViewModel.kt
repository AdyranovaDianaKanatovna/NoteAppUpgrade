package com.example.noteapp.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    protected fun <T> Flow<Resource<T>>.getData(
        error: (message: String?) -> Unit,
        loading: () -> Unit,
        success: (data: T?) -> Unit
    ){
        viewModelScope.launch(Dispatchers.IO) {
            collect(){
                when (it) {
                    is Resource.Error -> {
                        error(it.message)
                    }
                    is Resource.Loading -> {
                        loading()
                    }
                    is Resource.Success -> {
                        success(it.data)
                    }
                }
            }
        }
    }
}