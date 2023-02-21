package com.alex.lingvatranslate.ui.languageSelection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.lingvatranslate.domain.useCases.GetLanguagesUseCase
import com.alex.lingvatranslate.ui.translate.TranslateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(private val getLanguagesUseCase: GetLanguagesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(LanguageSelectionState())
    val uiState: StateFlow<LanguageSelectionState> = _uiState.asStateFlow()

    init {
        getLanguages()
    }

    private fun getLanguages() {
        viewModelScope.launch {
            _uiState.update {it.copy(languages = getLanguagesUseCase.execute()) }
        }
    }

}