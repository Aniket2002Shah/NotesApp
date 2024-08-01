package com.example.newsapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Insert(onConflict =OnConflictStrategy.IGNORE)
    suspend fun addNote(notes:Notes)

    /*@Update(onConflict =OnConflictStrategy.REPLACE)
    suspend fun updateNote(notes:Notes)*/

    @Delete
    suspend fun deleteNote(notes:Notes)

    @Query("select * from notes order by id asc")
    fun fetchAllNotes(): LiveData<List<Notes>>
}