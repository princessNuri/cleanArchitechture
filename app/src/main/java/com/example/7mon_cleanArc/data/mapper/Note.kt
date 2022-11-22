package com.example.`7mon_cleanArc`.data.mapper


import com.example.`7mon_cleanArc`.domain.model.Note
import com.example.`7mon_cleanArc`.data.model.NoteEntity

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