package com.alqiran.noteapp.feature_node.ui.add_edit_note_screen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.alqiran.noteapp.feature_node.domain.use_case.AddNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val noteUseCase: AddNoteUseCase) {

    private val _noteTitle = mutableStateOf(NoteTextFieldState())
    val noteTitle: State<NoteTextFieldState> = _noteTitle



}