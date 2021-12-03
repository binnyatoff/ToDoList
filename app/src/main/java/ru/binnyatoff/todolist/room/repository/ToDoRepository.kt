package ru.binnyatoff.todolist.room.repository

import androidx.lifecycle.LiveData
import ru.binnyatoff.todolist.room.data.ToDoDao
import ru.binnyatoff.todolist.room.model.ToDo
import javax.inject.Inject

class ToDoRepository @Inject constructor(private var toDoDao: ToDoDao) {
    val readAllData:LiveData<List<ToDo>> = toDoDao.readAllData()

    suspend fun addTodo(todo: ToDo){
        toDoDao.addTodo(todo)
    }

    suspend fun deleteTodo(todo: ToDo){
        toDoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: ToDo) {
        toDoDao.updateTodo(todo)
    }
    suspend fun doneTodo(id: Int, done: Boolean){
        toDoDao.doneTodo(id, done)
    }
}