package com.example.newsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application:Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Notes>>
    private val repo: NotesRepository

    init {
        val dao = NotesDatabase.getDatabase(application).notesDao()
        repo = NotesRepository(dao)
        allNotes = repo.allNotes
    }

    fun onDelete(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteNotes(notes)
    }

   /* fun onEdit(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateNotes(notes)
    }
    */

    fun insert(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repo.insertNotes(notes)
    }
}