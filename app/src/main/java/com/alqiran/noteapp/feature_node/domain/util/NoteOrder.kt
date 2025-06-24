package com.alqiran.noteapp.feature_node.domain.util


sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): NoteOrder(orderType)
    class Date(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is Color -> NoteOrder.Color(orderType)
            is Date -> NoteOrder.Date(orderType)
            is Title -> NoteOrder.Title(orderType)
        }
    }
}