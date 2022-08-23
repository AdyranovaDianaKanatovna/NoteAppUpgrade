package com.example.noteapp.domain.use_case

import com.example.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    fun getAllNotes() = noteRepository.getAllNote()

}