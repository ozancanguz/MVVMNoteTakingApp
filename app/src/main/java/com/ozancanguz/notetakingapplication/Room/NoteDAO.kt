package com.ozancanguz.notetakingapplication.Room

import androidx.room.*
import com.ozancanguz.notetakingapplication.Model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {

    @Insert
    suspend fun insert(note: Note) // coroutine suspend

    @Update
    suspend fun update(note:Note)

    @Delete
    suspend fun delete(note:Note)

    @Query("DELETE FROM note_table ")
    suspend fun deleteAllNotes()

    @Query("SELECT*FROM note_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>> // flow koyduk corotine





}