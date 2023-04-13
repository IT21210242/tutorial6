package com.example.tutorial6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())


        val repository = TodoRepository(TodoDatabase.getInstance(this))
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllTodos()
            todoAdapter.setData(data, this@MainActivity)
        }

        val toDoItems: RecyclerView = findViewById(R.id.rvTodoList)
        toDoItems.adapter =todoAdapter

        toDoItems.layoutManager = LinearLayoutManager(this)

        val addButton : Button = findViewById(R.id.btnAddTodo)

        addButton.setOnClickListener{
            val etTodoTitle: EditText = findViewById(R.id.etTodoTitle)
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)

                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(Todo(todoTitle))
                }

                etTodoTitle.text.clear()
            }
        }

        val btnDeleteDoneTodos : Button = findViewById(R.id.btnDeleteDoneTodo)

        btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}