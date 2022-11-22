package com.example.`7mon_cleanArc`.domain.usecase

import com.example.`7mon_cleanArc`.domain.model.Note
import com.example.`7mon_cleanArc`.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository){

    fun deleteNote(note: Note)= noteRepository.deleteNote(note)

}