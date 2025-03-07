package com.professorperson.lmm.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.professorperson.lmm.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)

    @Query("DELETE FROM note")
    fun deleteAll()

    @Delete
    fun delete(note: Note)
}