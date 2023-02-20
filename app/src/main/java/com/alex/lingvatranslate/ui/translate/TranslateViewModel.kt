package com.alex.lingvatranslate.ui.translate

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.lingvatranslate.data.TranslationRepository
import com.alex.lingvatranslate.model.Language
import com.alex.lingvatranslate.model.Languages
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

    fun updateTextToTranslate(input: String){
        _uiState.update { currentState -> currentState.copy(textToTranslate = input) }
    }

    fun updateSelectedSourceLanguage(input: Language){
        _uiState.update { currentState -> currentState.copy(selectedSourceLanguage = input) }
    }

    fun updateSelectedTargetLanguage(input: Language){
        _uiState.update { currentState -> currentState.copy(selectedTargetLanguage = input) }
    }

    fun translateText() {
        viewModelScope.launch {
            _uiState.update { currentState -> currentState.copy(translationResult = translationRepository.getTranslation(source = _uiState.value.selectedSourceLanguage.code, target = _uiState.value.selectedTargetLanguage.code, query = _uiState.value.textToTranslate)) }
        }
        Log.d("Translation", _uiState.value.translationResult.translation)
    }

    private fun getLanguages() {
        viewModelScope.launch {
            _uiState.update { currentState -> currentState.copy(languages = translationRepository.getLanguages()) }
        }
    }
}