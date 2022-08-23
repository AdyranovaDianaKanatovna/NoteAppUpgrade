package com.example.noteapp.domain.use_case

import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    val noteRepository: NoteRepository
) {

    fun addNote(note: Note) = noteRepository.addNote(note)
}