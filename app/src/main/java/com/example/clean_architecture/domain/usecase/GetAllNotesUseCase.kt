package com.example.clean_architecture.domain.usecase

import com.example.clean_architecture.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val noteRepository: NoteRepository){

    fun getAllNotes()= noteRepository.getAllNotes()

}