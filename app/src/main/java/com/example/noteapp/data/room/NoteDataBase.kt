package com.example.noteapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}