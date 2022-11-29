package com.example.clean_architecture.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.domain.usecase.DeleteNoteUseCase
import com.example.clean_architecture.domain.usecase.GetAllNotesUseCase
import com.example.clean_architecture.domain.utils.Resource
import com.example.clean_architecture.presentation.UIState
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
): ViewModel() {

    private val _getAllNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getAllNotesState = _getAllNotesState.asStateFlow()

    private val _deleteNoteState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNoteState = _deleteNoteState.asStateFlow()

    fun getAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllNotesUseCase.getAllNotes().collect{result->
                when ( result){
                    is Resource.Loading-> {
                        _getAllNotesState.value = UIState.Loading()
                    }
                    is Resource.Error->{
                        _getAllNotesState.value = UIState.Error(result.message!!)
                    }
                    is Resource.Success->{
                        if(result.data!=null)
                            _getAllNotesState.value=UIState.Success(result.data)
                    }
                    else -> {}
                }

            }

        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.deleteNote(note).collect{result->
                when ( result){
                    is Resource.Loading-> {
                        _deleteNoteState.value = UIState.Loading()
                    }
                    is Resource.Error->{
                        _deleteNoteState.value = UIState.Error(result.message!!)
                    }
                    is Resource.Success->{
                        if(result.data!=null)
                            _deleteNoteState.value=UIState.Success(result.data)
                    }
                    else -> {}
                }

            }

        }
    }
}