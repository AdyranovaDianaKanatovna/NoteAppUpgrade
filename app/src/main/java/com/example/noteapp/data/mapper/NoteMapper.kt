package com.example.noteapp.data.mapper

import com.example.noteapp.data.network.CharacterDto
import com.example.noteapp.data.network.InfoDto
import com.example.noteapp.data.network.MainResultDto
import com.example.noteapp.data.room.NoteEntity
import com.example.noteapp.domain.model.network.Character
import com.example.noteapp.domain.model.network.Info
import com.example.noteapp.domain.model.network.MainResult
import com.example.noteapp.domain.model.room.Note

class NoteMapper {

    fun toNoteEntity(note: Note): NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            description = note.description
        )
    }

    fun toNote(noteEntity: NoteEntity): Note {
        return Note(
            id = noteEntity.id,
            title = noteEntity.title,
            description = noteEntity.description
        )
    }

    fun toCharacterDto(character: Character) = CharacterDto(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        gender = character.gender,
        image = character.image,
    )

    fun toCharacter(characterDto: CharacterDto) = Character(
        id = characterDto.id,
        name = characterDto.name,
        status = characterDto.status,
        species = characterDto.species,
        gender = characterDto.gender,
        image = characterDto.image,
    )

    fun toInfo(infoDto: InfoDto) = Info(
        count = infoDto.count,
        next = infoDto.next,
        pages = infoDto.pages,
    )

    fun toMainResult(mainResultDto: MainResultDto<CharacterDto>) = MainResult(
        info = mainResultDto.info?.let { toInfo(it) },
        result = mainResultDto.result.map {
            toCharacter(it)
        }
    )
}