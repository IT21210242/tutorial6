package com.example.tutorial6

class TodoRepository(
    private val db: TodoDatabase
) {
    suspend fun insert(todo: Todo) = db.getTodoDao().insertTodo(todo)
    suspend fun delete(todo: Todo) = db.getTodoDao().delete(todo)
    suspend fun update(todo: Todo) = db.getTodoDao().updatetodo(todo)
    fun getAllTodos() =db.getTodoDao().getAlltodos()
}