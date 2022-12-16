package com.example.clean_architecture.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.domain.usecase.CreateNoteUseCase
import com.example.clean_architecture.domain.usecase.EditNoteUseCase
import com.example.clean_architecture.domain.utils.Resource
import com.example.clean_architecture.presentation.UIState
import com.example.clean_architecture.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteFormViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
): BaseViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(note : Note){
        createNoteUseCase(note).collectFlow(_createNoteState)
    }
    fun editNote(note : Note){
        editNoteUseCase(note).collectFlow(_editNoteState)
    }
}