package ru.binnyatoff.todolist.screens.fragments.list.adapter

import ru.binnyatoff.todolist.room.model.ToDo

interface ListAdapterDelegate {
    fun todoClick(todo: ToDo)
    fun todoDelete(todo: ToDo)
    fun doneTodo(id: Int, done: Boolean)
}