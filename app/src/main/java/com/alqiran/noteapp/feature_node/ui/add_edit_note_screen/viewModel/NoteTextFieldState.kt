package com.alqiran.noteapp.feature_node.ui.add_edit_note_screen.viewModel

data class NoteTextFieldState (
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
)