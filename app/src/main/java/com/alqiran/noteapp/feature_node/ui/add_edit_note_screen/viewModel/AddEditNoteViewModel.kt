package com.alqiran.noteapp.feature_node.ui.add_edit_note_screen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.noteapp.feature_node.domain.model.InvalidNoteException
import com.alqiran.noteapp.feature_node.domain.model.Note
import com.alqiran.noteapp.feature_node.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNoteUseCase(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.intValue = note.color

                    }
                }
            }
        }
    }

    private var currentNoteId: Int? = null

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter Note Title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter the Content..."
        )
    )
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableIntStateOf(Note.noteColors.random().toArgb())
    val noteColor: State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<EventUi>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.intValue = event.color
            }

            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }

            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }

            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }

            AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNoteUseCase(
                            Note(
                                id = currentNoteId,
                                title = _noteTitle.value.text,
                                color = _noteColor.intValue,
                                content = _noteContent.value.text,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFlow.emit(EventUi.SaveNote)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            EventUi.ShowSnackBar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

}