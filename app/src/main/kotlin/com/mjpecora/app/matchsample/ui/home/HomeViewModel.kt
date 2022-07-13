package com.mjpecora.app.matchsample.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjpecora.app.domain.model.ApodDomain
import com.mjpecora.app.domain.usecase.ApodUseCase
import com.mjpecora.app.matchsample.base.Reducer
import com.mjpecora.app.matchsample.base.UiEvent
import com.mjpecora.app.matchsample.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apodUseCase: ApodUseCase
) : ViewModel() {

    private val reducer = homeReducer

    val state: Flow<HomeState> = reducer.state

    init {
        viewModelScope.launch {
            sendEvent(HomeEvent.OnApodLoad(apodUseCase.invoke()))
        }
    }

    fun sendEvent(event: HomeEvent) {
        reducer.sendEvent(event)
    }

    companion object {
        private val homeReducer = object : Reducer<HomeState, HomeEvent>(HomeState()) {
            override fun reduce(oldState: HomeState, event: HomeEvent) {
                when (event) {
                    is HomeEvent.OnApodLoad -> setState(oldState.copy(isLoading = false, apod = event.apod))
                }
            }
        }
    }

}

data class HomeState(
    val isLoading: Boolean = false,
    val apod: ApodDomain? = null
) : UiState

sealed class HomeEvent : UiEvent {
    data class OnApodLoad(val apod: ApodDomain) : HomeEvent()
}