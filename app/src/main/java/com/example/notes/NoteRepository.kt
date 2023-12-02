package com.example.notes


class NoteRepository(private val noteDao: NoteDao) {
    val allNotes = noteDao.getAllNotes()

    suspend fun insert(notes: Notes){
        noteDao.insert(notes)
    }

    suspend fun delete(notes: Notes){
        noteDao.delete(notes)
    }

}