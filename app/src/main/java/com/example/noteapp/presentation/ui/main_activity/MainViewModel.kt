package com.example.noteapp.presentation.ui.main_activity

import com.example.noteapp.core.BaseViewModel
import com.example.noteapp.core.UIState
import com.example.noteapp.domain.model.room.Note
import com.example.noteapp.domain.use_case.AddNoteUseCase
import com.example.noteapp.domain.use_case.DeleteNoteUseCase
import com.example.noteapp.domain.use_case.GetAllNotesUseCase
import com.example.noteapp.domain.use_case.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : BaseViewModel() {

    private val _notesState = MutableStateFlow<UIState<List<Note>>>(UIState.Loading())
    val noteState = _notesState.asStateFlow()

    private val _addNotesState = MutableStateFlow<UIState<Unit>>(UIState.Loading())
    val addNotesState = _addNotesState.asStateFlow()

    private val _deleteNotesState = MutableStateFlow<UIState<Unit>>(UIState.Loading())
    val deleteNotesState = _deleteNotesState.asStateFlow()

    private val _updateNoteState = MutableStateFlow<UIState<Unit>>(UIState.Loading())
    val updateNoteState = _updateNoteState.asStateFlow()

    fun addNote(note: Note) {
        addNoteUseCase.addNote(note).getData(
            { errorMessage ->
                _addNotesState.value = UIState.Error(errorMessage ?: "Error")
            },
            {
                _addNotesState.value = UIState.Loading()
            },
            { data ->
                if (data != null) {
                    _addNotesState.value = UIState.Success(data)
                }
            })
    }

    fun deleteNote(note: Note) {
        deleteNoteUseCase.deleteNote(note).getData(
            { errorMessage ->
                _deleteNotesState.value = UIState.Error(errorMessage ?: "Error")
            },
            {
                _deleteNotesState.value = UIState.Loading()
            },
            { data ->
                if (data != null) {
                    _deleteNotesState.value = UIState.Success(data)
                }
            })
    }

    fun getAllNotes() {
        getAllNotesUseCase.getAllNotes().getData(
            { errorMessage ->
                _notesState.value = UIState.Error(errorMessage ?: "Error")
            },
            {
                _notesState.value = UIState.Loading()
            },
            { data ->
                if (data != null) {
                    _notesState.value = UIState.Success(data)
                }
            })
    }
    fun updateNote(note: Note) {
        updateNoteUseCase.updateNote(note).getData(
            { errorMessage ->
                _updateNoteState.value = UIState.Error(errorMessage ?: "Error")
            },
            {
                _updateNoteState.value = UIState.Loading()
            },
            { data ->
                if (data != null) {
                    _updateNoteState.value = UIState.Success(data)
                }
            })
    }
}