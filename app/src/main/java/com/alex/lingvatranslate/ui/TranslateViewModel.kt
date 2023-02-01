package com.alex.lingvatranslate.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.alex.lingvatranslate.LingvaTranslateApplication
import com.alex.lingvatranslate.data.TranslationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TranslateViewModel(private val translationRepository: TranslationRepository) : ViewModel() {

    // UI state
    private val _uiState = MutableStateFlow(TranslateUiState())
    val uiState: StateFlow<TranslateUiState> = _uiState.asStateFlow()

    var textToTranslate by mutableStateOf("")
        private set

    init {
        getLanguages()
    }

    fun getLanguages() {
        viewModelScope.launch {
            try {
                _uiState.update { currenState ->
                    currenState.copy(
                        languages = translationRepository.getLanguages()
                    )
                }

                uiState.value.languages.forEach() {
                    Log.println(Log.DEBUG, null, it.name)
                }

            } catch (ex: Exception) {
                Log.println(Log.ERROR, null, ex.message.toString())
            }
        }
    }

    fun updateTextToTranslate(text: String) {
        textToTranslate = text
        Log.println(Log.DEBUG, null, "Text: $textToTranslate")
    }

    fun translateText() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _uiState.update { currenState ->
                    currenState.copy(
                        translation = translationRepository.getTranslation(
                            "de",
                            "ru",
                            textToTranslate
                        ).translation
                    )
                }
            } catch (ex: Exception) {
                Log.println(Log.ERROR, null, ex.message.toString())
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LingvaTranslateApplication)
                val translationRepository = application.container.translationRepository
                TranslateViewModel(translationRepository = translationRepository)
            }
        }
    }
}