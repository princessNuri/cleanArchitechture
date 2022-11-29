package com.example.clean_architecture.di

import android.content.Context
import androidx.room.Room
import com.example.clean_architecture.data.localdb.NoteDao
import com.example.clean_architecture.data.repository.NoteRepositoryImpl
import com.example.clean_architecture.data.localdb.NoteDataBase
import com.example.clean_architecture.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NoteModule {


    @Singleton
    @Provides
    fun provideNoteDataBase(
        @ApplicationContext context: Context
    ):NoteDataBase=Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            "note_db"
        ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideNoteDao (noteDataBase: NoteDataBase)=noteDataBase.noteDao()

    @Provides
    fun provideNoteRepository(noteDao: NoteDao):NoteRepository{
        return NoteRepositoryImpl(noteDao)
    }
}