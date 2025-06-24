package com.alqiran.noteapp.feature_node.domain.use_case

import com.alqiran.noteapp.feature_node.domain.model.Note
import com.alqiran.noteapp.feature_node.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}