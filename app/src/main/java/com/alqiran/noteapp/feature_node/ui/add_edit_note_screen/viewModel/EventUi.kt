package com.alqiran.noteapp.feature_node.ui.add_edit_note_screen.viewModel

sealed class EventUi {
    object SaveNote: EventUi()
    data class ShowSnackBar(val message: String): EventUi()
}