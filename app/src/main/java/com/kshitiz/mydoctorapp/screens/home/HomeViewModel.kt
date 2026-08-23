package com.kshitiz.mydoctorapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kshitiz.mydoctorapp.data.DoctorRepositoryImpl
import com.kshitiz.mydoctorapp.model.Doctor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    data class Loading(val isFirstLoad: Boolean) : HomeUiState
    data class Success(val doctors: List<Doctor>, val filter: String) : HomeUiState
    data class Error(val message: String, val retry: () -> Unit) : HomeUiState
}

class HomeViewModel(private val repository: DoctorRepositoryImpl = DoctorRepositoryImpl()) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading(true))
    val uiState = _uiState.asStateFlow()

    private var currentFilter = "All doctors"

    init {
        loadDoctors()
    }

    fun loadDoctors() {
        viewModelScope.launch {
            val isFirstLoad = _uiState.value !is HomeUiState.Success
            _uiState.value = HomeUiState.Loading(isFirstLoad)
            repository.getAllDoctors().onSuccess { doctors ->
                _uiState.value = HomeUiState.Success(doctors, currentFilter)
            }.onFailure { e ->
                _uiState.value = HomeUiState.Error(e.message ?: "Failed to load doctors", { loadDoctors() })
            }
        }
    }

    fun setFilter(filter: String) {
        currentFilter = filter
        _uiState.value = when (val state = _uiState.value) {
            is HomeUiState.Success -> state.copy(filter = filter)
            else -> state
        }
    }
}