package com.kshitiz.mydoctorapp.screens.doctordetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kshitiz.mydoctorapp.data.DoctorRepositoryImpl
import com.kshitiz.mydoctorapp.model.Doctor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val doctor: Doctor) : DetailUiState
    data class Error(val message: String, val retry: () -> Unit) : DetailUiState
}

class DoctorDetailViewModel(
    private val doctorId: Int,
    private val repository: DoctorRepositoryImpl = DoctorRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadDoctor()
    }

    fun loadDoctor() {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            repository.getDoctorById(doctorId).onSuccess { doctor ->
                doctor?.let {
                    _uiState.value = DetailUiState.Success(it)
                } ?: run {
                    _uiState.value = DetailUiState.Error("Doctor not found", { loadDoctor() })
                }
            }.onFailure { e ->
                _uiState.value = DetailUiState.Error(e.message ?: "Failed to load doctor", { loadDoctor() })
            }
        }
    }
}

class DoctorDetailViewModelFactory(private val doctorId: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DoctorDetailViewModel(doctorId) as T
    }
}