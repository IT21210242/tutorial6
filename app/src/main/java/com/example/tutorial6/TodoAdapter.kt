package com.example.tutorial6

import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    lateinit var data: List<Todo>
    lateinit var context: Context

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    fun setData(data: List<Todo>, context: Context) {
        var i=0;
        for(x in data){
            todos.add(x)
            notifyItemInserted(todos.size - 1)
        }

        this.data = data
        this.context = context
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = todos[position]
        holder.itemView.apply {
            var textTitle:TextView = findViewById(R.id.tvTodoTitle)
            textTitle.text = currTodo.title

            var checkBox: CheckBox = findViewById(R.id.cbDone)
            checkBox.isChecked = currTodo.isChecked

            toggleStrikeThrough(textTitle, checkBox.isChecked)
            checkBox.setOnCheckedChangeListener{_,isChecked->
                toggleStrikeThrough(textTitle,isChecked)
                currTodo.isChecked=!currTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}