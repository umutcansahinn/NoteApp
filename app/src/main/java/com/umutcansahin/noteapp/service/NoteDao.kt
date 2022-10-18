package com.umutcansahin.noteapp.service

import androidx.room.*
import com.umutcansahin.noteapp.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("UPDATE note_table SET title = :newTitle, note = :newNote WHERE id = :newId")
    suspend fun update(newTitle: String, newNote: String, newId: Int)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    suspend fun getAllNote(): List<Note>

    @Query("SELECT * FROM note_table WHERE id = :noteId ")
    suspend fun getNote(noteId: Int): Note
}