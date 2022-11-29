package com.example.clean_architecture.data.repository

import com.example.clean_architecture.data.base.BaseRepository
import com.example.clean_architecture.data.localdb.NoteDao
import com.example.clean_architecture.data.mapper.toEntity
import com.example.clean_architecture.data.mapper.toNote
import com.example.clean_architecture.domain.repository.NoteRepository
import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor (private val noteDao : NoteDao )
    : NoteRepository, BaseRepository() {

    override fun createNote(note: Note) = doRequest {
        noteDao.createNote(note.toEntity())
    }

    override fun getAllNotes () = doRequest {
                noteDao.getAllNotes().map {
                    it.toNote()
                }}
//        return noteDao.getAllNotes().map {
//            it.toNote()
//        }

    override fun editNote(note: Note) = doRequest {
        noteDao.editNote(note.toEntity())
    }

    override fun deleteNote(note: Note) = doRequest {
        noteDao.deleteNote(note.toEntity())
    }
}