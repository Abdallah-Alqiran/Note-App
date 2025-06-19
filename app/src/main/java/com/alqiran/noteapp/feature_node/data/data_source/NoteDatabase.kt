package com.alqiran.noteapp.feature_node.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alqiran.noteapp.feature_node.domain.model.Note

@Database(entities = [Note::class], exportSchema = false, version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
}