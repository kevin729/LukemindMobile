package com.professorperson.lmm.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.professorperson.lmm.models.daos.NoteDao
import com.professorperson.lmm.models.Note

@Database(entities = [Note::class], version = 1)
abstract class LMDatabase : RoomDatabase() {
    abstract fun userDao() : NoteDao
}