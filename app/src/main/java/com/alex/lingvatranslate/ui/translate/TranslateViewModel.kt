package com.alex.lingvatranslate.ui.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.lingvatranslate.data.TranslationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(private val translationRepository: TranslationRepository) : ViewModel() {
    private val _state = MutableStateFlow(TranslateState())

    val state : StateFlow<TranslateState>
        get() = _state

    init {
        getLanguages()
    }

    private fun getLanguages() {
        viewModelScope.launch {
            TranslateState(languages = translationRepository.getLanguages())
        }
    }
}