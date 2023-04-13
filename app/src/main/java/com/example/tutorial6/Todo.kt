package com.example.tutorial6

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey
    val title: String,
    var isChecked: Boolean = false
)