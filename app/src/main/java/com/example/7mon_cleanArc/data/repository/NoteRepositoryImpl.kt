package com.example.`7mon_cleanArc`.data.repository

import com.example.`7mon_cleanArc`.data.localdb.NoteDao
import com.example.`7mon_cleanArc`.data.mapper.toEntity
import com.example.`7mon_cleanArc`.data.mapper.toNote
import com.example.`7mon_cleanArc`.domain.repository.NoteRepository
import com.example.`7mon_cleanArc`.domain.model.Note
import com.example.`7mon_cleanArc`.data.model.NoteEntity

class NoteRepositoryImpl (private val noteDao : NoteDao ) : NoteRepository{

    override fun createNote(note: Note){
        noteDao.createNote(note.toEntity())
    }
    override fun getAllNotes (): List<Note>{
        return noteDao.getAllNotes().map {
            it.toNote()
        }
    }
    override fun editNote(note: Note){
        noteDao.editNote(note.toEntity())
    }
    override fun deleteNote(note: Note){
        noteDao.deleteNote(note.toEntity())
    }

}