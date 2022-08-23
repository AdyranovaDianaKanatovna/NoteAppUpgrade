package com.example.noteapp.domain.use_case

import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase
@Inject constructor(
    val noteRepository: NoteRepository
) {

    fun deleteNote(note: Note) = noteRepository.deleteNote(note)
}