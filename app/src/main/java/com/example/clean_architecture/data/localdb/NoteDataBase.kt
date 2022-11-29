package com.example.clean_architecture.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clean_architecture.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase :RoomDatabase(){
    abstract fun noteDao():NoteDao
}