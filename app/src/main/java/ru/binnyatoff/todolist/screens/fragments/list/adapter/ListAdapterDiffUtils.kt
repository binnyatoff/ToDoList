package ru.binnyatoff.todolist.screens.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.binnyatoff.todolist.room.model.ToDo

class ListAdapterDiffUtils(private val oldList: List<ToDo>, private val newList: List<ToDo>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newTodo = newList[newItemPosition]
        return oldToDo.id == newTodo.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldToDo = oldList[oldItemPosition]
        val newTodo = newList[newItemPosition]
        return oldToDo == newTodo
    }
}