package com.example.noteapp.domain.use_case

import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class UpdateNoteUseCase@Inject constructor(
    val noteRepository: NoteRepository
) {

    fun updateNote(note: Note) = noteRepository.updateNote(note)
}