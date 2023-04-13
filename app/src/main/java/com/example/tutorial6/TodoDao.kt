package com.example.tutorial6

import androidx.room.*

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodo(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)
    @Update
    suspend fun updatetodo(todo: Todo)
    @Query("SELECT * From Todo")
    fun getAlltodos(): List<Todo>
}