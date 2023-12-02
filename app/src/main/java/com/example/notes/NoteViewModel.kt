package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    var allNotes: LiveData<List<Notes>>? = null
    val repository:NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note:Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun insertNote(note:Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}