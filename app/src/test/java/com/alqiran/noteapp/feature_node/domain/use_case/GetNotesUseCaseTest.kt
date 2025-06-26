package com.alqiran.noteapp.feature_node.domain.use_case

import com.alqiran.noteapp.feature_node.data.repository.NoteRepositoryImplTest
import com.alqiran.noteapp.feature_node.domain.model.Note
import com.alqiran.noteapp.feature_node.domain.repository.NoteRepository
import com.alqiran.noteapp.feature_node.domain.util.NoteOrder
import com.alqiran.noteapp.feature_node.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat

import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {

    private lateinit var getNotes: GetNotesUseCase
    private lateinit var fakeNoteRepository: NoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = NoteRepositoryImplTest()
        getNotes = GetNotesUseCase(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()

        for (i in 0..100) {
            notesToInsert.add(
                Note(
                    title = "$i Title",
                    content = "$i Content",
                    timestamp = i.toLong(),
                    color = i
                )
            )
        }

        notesToInsert.shuffle()

        runBlocking {
            notesToInsert.forEach { note ->
                fakeNoteRepository.insertNote(note)
            }
        }
    }

    @Test
    fun `Order notes by title Ascending, correct Order`() = runBlocking {
        val note = getNotes(noteOrder = NoteOrder.Title(OrderType.Ascending)).first()
        for (n in 0..note.size -2) {
            assertThat(note[n].title).isLessThan(note[n+1].title)
        }
    }

    @Test
    fun `Order notes by Title Descending, correct Order`() = runBlocking {
        val note = getNotes(noteOrder = NoteOrder.Title(OrderType.Descending)).first()
        for (n in 0..note.size-2) {
            assertThat(note[n].title).isGreaterThan(note[n+1].title)
        }
    }

    @Test
    fun `Order notes by Color Ascending, correct Order`() = runBlocking {
        val note = getNotes(noteOrder = NoteOrder.Color(OrderType.Ascending)).first()
        for (n in 0..note.size - 2) {
            assertThat(note[n].color).isLessThan(note[n+1].color)
        }
    }

    @Test
    fun `Order notes by Color Descending, correct Order`() = runBlocking {
        val note = getNotes(noteOrder = NoteOrder.Color(OrderType.Descending)).first()
        for (n in 0..note.size-2) {
            assertThat(note[n].color).isGreaterThan(note[n+1].color)
        }
    }

    @Test
    fun `Order notes by Date Ascending, correct Order`() = runBlocking {
        val note = getNotes(noteOrder = NoteOrder.Date(OrderType.Ascending)).first()
        for (n in 0..note.size - 2) {
            assertThat(note[n].timestamp).isLessThan(note[n+1].timestamp)
        }
    }

    @Test
    fun `Order notes by Date Descending, correct Order`() = runBlocking {
        val note = getNotes(noteOrder = NoteOrder.Date(OrderType.Descending)).first()
        for (n in 0..note.size - 2) {
            assertThat(note[n].timestamp).isGreaterThan(note[n+1].timestamp)
        }
    }
}