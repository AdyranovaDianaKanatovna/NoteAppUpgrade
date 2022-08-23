package com.example.noteapp.data.repository

import com.example.noteapp.core.Resource
import com.example.noteapp.data.base.BaseRepository
import com.example.noteapp.data.mapper.NoteMapper
import com.example.noteapp.data.network.RickAndMortyApiService
import com.example.noteapp.data.room.NoteDao
import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.data.network.CharacterDto
import com.example.noteapp.data.network.MainResultDto
import com.example.noteapp.domain.model.network.Character
import com.example.noteapp.domain.model.network.MainResult
import com.example.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val apiService: RickAndMortyApiService
) : NoteRepository, BaseRepository() {
    private val noteMapper = NoteMapper()

//    override fun getAllCharacters(): Flow<Resource<MainResult<Character>>> = doRequest {
//        noteMapper.toMainResult(apiService.getAllCharacters())
//    }

    override fun addNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.addNote(noteMapper.toNoteEntity(note))
    }

    override fun getAllNote(): Flow<Resource<List<Note>>> = doRequest {
        noteDao.getAllNotes().map { noteEntity ->
            noteMapper.toNote(noteEntity)
        }
    }

    override fun deleteNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.deleteNote(noteMapper.toNoteEntity(note))
    }

    override fun updateNote(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.updateNote(noteMapper.toNoteEntity(note))
    }

}