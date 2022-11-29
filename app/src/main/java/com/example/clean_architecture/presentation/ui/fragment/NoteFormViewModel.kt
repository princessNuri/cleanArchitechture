package com.example.clean_architecture.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.domain.usecase.CreateNoteUseCase
import com.example.clean_architecture.domain.usecase.EditNoteUseCase
import com.example.clean_architecture.domain.utils.Resource
import com.example.clean_architecture.presentation.UIState
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
): ViewModel() {

    private val _createNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNoteState = _createNoteState.asStateFlow()

    private val _editNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNoteState = _editNoteState.asStateFlow()

    fun createNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            createNoteUseCase.createNote(note).collect{result->
                when ( result){
                    is Resource.Loading-> {
                        _createNoteState.value = UIState.Loading()
                    }
                    is Resource.Error->{
                        _createNoteState.value = UIState.Error(result.message!!)
                    }
                    is Resource.Success->{
                        if(result.data!=null)
                        _createNoteState.value=UIState.Success(result.data)
                    }
                    else -> {}
                }

            }

        }
    }
    fun editNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            editNoteUseCase.editNote(note).collect{result->
                when ( result){
                    is Resource.Loading-> {
                        _editNoteState.value = UIState.Loading()
                    }
                    is Resource.Error->{
                        _editNoteState.value = UIState.Error(result.message!!)
                    }
                    is Resource.Success->{
                        if(result.data!=null)
                            _editNoteState.value=UIState.Success(result.data)
                    }
                    else -> {}
                }

            }

        }
    }
}