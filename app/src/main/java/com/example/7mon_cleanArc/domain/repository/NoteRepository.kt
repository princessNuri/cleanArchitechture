package com.example.`7mon_cleanArc`.domain.repository


import com.example.`7mon_cleanArc`.domain.model.Note
interface NoteRepository {

    fun createNote(note: Note)

    fun getAllNotes (): List<Note>

    fun editNote(note: Note)

    fun deleteNote(note: Note)
}