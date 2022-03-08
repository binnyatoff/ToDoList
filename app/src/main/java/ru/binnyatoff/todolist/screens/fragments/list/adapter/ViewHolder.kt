package ru.binnyatoff.todolist.screens.fragments.list.adapter

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.room.model.ToDo

class ViewHolder(itemView: View, private val delegate: ListAdapterDelegate?) :
    RecyclerView.ViewHolder(itemView) {
    val todo_item: View = itemView.findViewById(R.id.todo_item)
    var name_todo: TextView = itemView.findViewById(R.id.name_todo)
    var text_todo: TextView = itemView.findViewById(R.id.text_todo)
    var done_todo: CheckBox = itemView.findViewById(R.id.done_todo) as CheckBox

    fun bind(item: ToDo) {
        name_todo.text = item.id.toString()
        //name_todo.text = item.name_todo
        text_todo.text = item.text
        done_todo.isChecked = item.done

        if (item.done){
            name_todo.paintFlags = name_todo.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            name_todo.paintFlags = name_todo.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        done_todo.setOnClickListener {
            delegate?.doneTodo(item.id, done_todo.isChecked)
        }
        todo_item.setOnClickListener {
            delegate?.todoClick(item)
        }
    }
}