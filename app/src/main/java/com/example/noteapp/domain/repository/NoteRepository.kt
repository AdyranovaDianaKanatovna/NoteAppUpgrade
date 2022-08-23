package com.example.noteapp.domain.repository

import com.example.noteapp.core.Resource
import com.example.noteapp.data.network.CharacterDto
import com.example.noteapp.data.network.MainResultDto
import com.example.noteapp.domain.model.network.Character
import com.example.noteapp.domain.model.network.MainResult
import com.example.noteapp.domain.model.room.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun addNote(note: Note): Flow<Resource<Unit>>
    fun getAllNote(): Flow<Resource<List<Note>>>
    fun deleteNote(note: Note): Flow<Resource<Unit>>
    fun updateNote(note: Note): Flow<Resource<Unit>>

//    fun getAllCharacters(): Flow<Resource<MainResult<Character>>>
}