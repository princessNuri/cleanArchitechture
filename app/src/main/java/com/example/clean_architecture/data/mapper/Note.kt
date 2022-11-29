package com.example.clean_architecture.data.mapper


import com.example.clean_architecture.domain.model.Note
import com.example.clean_architecture.data.model.NoteEntity

fun Note.toEntity()=NoteEntity(
    id,
    title,
    description,
    createdAt
)
fun NoteEntity.toNote()=Note(
    id,
    title,
    description,
    createdAt
)