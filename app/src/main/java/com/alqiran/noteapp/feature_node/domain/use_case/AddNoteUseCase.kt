package com.alqiran.noteapp.feature_node.domain.use_case

import com.alqiran.noteapp.feature_node.domain.model.InvalidNoteException
import com.alqiran.noteapp.feature_node.domain.model.Note
import com.alqiran.noteapp.feature_node.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The Title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}