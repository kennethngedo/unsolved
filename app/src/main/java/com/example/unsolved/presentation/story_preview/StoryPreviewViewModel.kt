package com.example.unsolved.presentation.story_preview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsolved.R
import com.example.unsolved.common.utils.Resource
import com.example.unsolved.common.utils.UIEvent
import com.example.unsolved.common.utils.UIText
import com.example.unsolved.domain.usecases.GetStory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryPreviewViewModel @Inject constructor(private val getStory: GetStory) : ViewModel() {

    private val _state = mutableStateOf(StoryState())
    val state: State<StoryState> = _state

    private val _event = MutableSharedFlow<UIEvent>()
    val event = _event.asSharedFlow()

    fun performGetStory() {
        viewModelScope.launch(Dispatchers.IO) {
            getStory().collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            story = it.data
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            story = it.data
                        )
                    }
                    is Resource.Failure -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            story = it.data
                        )
                        _event.emit(
                            UIEvent.ShowSnackBar
                        )
                    }
                }
            }
        }
    }
}