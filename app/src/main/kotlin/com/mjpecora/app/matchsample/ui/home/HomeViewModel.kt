package com.mjpecora.app.matchsample.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mjpecora.app.domain.usecase.ApodUseCase
import com.mjpecora.app.domain.usecase.MarsRoverUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apodUseCase: ApodUseCase,
    private val marsRoverUseCase: MarsRoverUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        _uiState.value = HomeUiState.Loading
        loadApodInitialState()
        loadMarsRoverInitialState()
    }

    private fun loadApodInitialState() {
        viewModelScope.launch {
            val response = apodUseCase.invoke()
            (_uiState.value as? HomeUiState.Loaded)?.let {
                _uiState.value = it.copy(apodData = response)
            } ?: run {
                _uiState.value = HomeUiState.Loaded(response, null)
            }
        }
    }

    private fun loadMarsRoverInitialState() {
        val photosFlow = marsRoverUseCase.invoke().cachedIn(viewModelScope)
        (_uiState.value as? HomeUiState.Loaded)?.let {
            _uiState.value = it.copy(marsRoverFlow = photosFlow)
        } ?: run {
            _uiState.value = HomeUiState.Loaded(null,photosFlow)
        }
    }

}