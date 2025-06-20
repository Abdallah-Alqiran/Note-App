package com.alqiran.noteapp.feature_node.domain.util

sealed class OrderType {
    object Descending: OrderType()
    object Ascending: OrderType()
}