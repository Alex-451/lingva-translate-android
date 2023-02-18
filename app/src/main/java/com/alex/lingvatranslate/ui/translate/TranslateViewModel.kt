package com.alex.lingvatranslate.ui.translate

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.lingvatranslate.data.TranslationRepository
import com.alex.lingvatranslate.model.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(private val translationRepository: TranslationRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(TranslateState())
    val uiState: StateFlow<TranslateState> = _uiState.asStateFlow()

    init {
        getLanguages()
    }

    private fun getLanguages() {
        viewModelScope.launch {
            _uiState.update { currentState -> currentState.copy(languages = translationRepository.getLanguages()) }
        }
    }
}