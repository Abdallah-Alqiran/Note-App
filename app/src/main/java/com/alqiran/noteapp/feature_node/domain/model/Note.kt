package com.alqiran.noteapp.feature_node.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alqiran.noteapp.theme.BabyBlue
import com.alqiran.noteapp.theme.LightGreen
import com.alqiran.noteapp.theme.RedOrange
import com.alqiran.noteapp.theme.RedPink
import com.alqiran.noteapp.theme.Violet

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)