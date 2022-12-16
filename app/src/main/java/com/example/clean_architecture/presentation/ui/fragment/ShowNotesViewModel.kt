package com.example.clean_architecture.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.domain.usecase.DeleteNoteUseCase
import com.example.clean_architecture.domain.usecase.GetAllNotesUseCase
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
class ShowNotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
): BaseViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNoteState = _deleteNoteState.asStateFlow()

    fun getAllNotes(){
        getAllNotesUseCase().collectFlow(_getAllNotesState)

    }
    fun deleteNote(note: Note){
        deleteNoteUseCase(note).collectFlow(_deleteNoteState)
    }
}