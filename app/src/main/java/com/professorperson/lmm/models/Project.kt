package com.professorperson.lmm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "introduction") val introduction: String,
    @ColumnInfo(name = "Target Audiences") val target_audiences: String
)
