package com.alqiran.noteapp.feature_node.data.repository

import com.alqiran.noteapp.feature_node.data.data_source.NoteDao
import com.alqiran.noteapp.feature_node.domain.model.Note
import com.alqiran.noteapp.feature_node.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNotesById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}