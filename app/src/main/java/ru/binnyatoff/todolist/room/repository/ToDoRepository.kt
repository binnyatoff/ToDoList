package ru.binnyatoff.todolist.room.repository

import androidx.lifecycle.LiveData
import ru.binnyatoff.todolist.room.data.ToDoDao
import ru.binnyatoff.todolist.model.ToDo

class ToDoRepository(private val ToDoDao: ToDoDao) {
    val readAllData:LiveData<List<ToDo>> = ToDoDao.readAllData()

    suspend fun addTodo(todo: ToDo){
        ToDoDao.addTodo(todo)
    }

    suspend fun deleteTodo(todo: ToDo){
        ToDoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: ToDo) {
        ToDoDao.updateTodo(todo)
    }
    suspend fun doneTodo(id: Int, done: Boolean){
        ToDoDao.doneTodo(id, done)
    }
}