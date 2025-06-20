package com.alqiran.noteapp.feature_node.domain.use_case

import com.alqiran.noteapp.feature_node.domain.model.Note
import com.alqiran.noteapp.feature_node.domain.repository.NoteRepository
import com.alqiran.noteapp.feature_node.domain.util.NoteOrder
import com.alqiran.noteapp.feature_node.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
        return repository.getNotes().map { note ->
            when(noteOrder.orderType) {
                OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Color -> note.sortedBy { it.color }
                        is NoteOrder.Date -> note.sortedBy { it.timestamp }
                        is NoteOrder.Title -> note.sortedBy { it.title.lowercase() }
                    }
                }
                OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Color -> note.sortedByDescending { it.color }
                        is NoteOrder.Date -> note.sortedByDescending { it.timestamp }
                        is NoteOrder.Title -> note.sortedByDescending { it.title.lowercase() }
                    }
                }
            }
        }
    }
}