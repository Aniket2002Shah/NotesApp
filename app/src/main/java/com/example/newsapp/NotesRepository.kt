package com.example.newsapp

import androidx.lifecycle.LiveData

class NotesRepository(private val dao: NotesDao) {
   val  allNotes:LiveData<List<Notes>> = dao.fetchAllNotes()

    suspend fun insertNotes(notes:Notes){
        dao.addNote(notes)
    }

   /* suspend fun updateNotes(notes:Notes){
      dao.updateNote(notes)
      }
      */


    suspend fun deleteNotes(notes:Notes){
        dao.deleteNote(notes)
    }

}