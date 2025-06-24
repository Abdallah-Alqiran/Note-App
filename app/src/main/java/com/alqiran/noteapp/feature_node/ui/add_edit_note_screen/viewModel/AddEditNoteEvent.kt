package com.alqiran.noteapp.feature_node.ui.add_edit_note_screen.viewModel

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    object SaveNote: AddEditNoteEvent()

    data class EnteredTitle(val value: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()

    data class EnteredContent(val value: String): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()

    data class ChangeColor(val color: Int): AddEditNoteEvent()
}