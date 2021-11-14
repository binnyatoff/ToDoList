package ru.binnyatoff.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ru.binnyatoff.todolist.room.data.ToDoDatabase
import ru.binnyatoff.todolist.room.repository.ToDoRepository
import ru.binnyatoff.todolist.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<ToDo>>
    private val repository: ToDoRepository

    init {
        val todoDao = ToDoDatabase.getDatabase(application).todoDao()
        repository = ToDoRepository(todoDao)
        readAllData = repository.readAllData
    }

    fun addToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }

    fun deleteToDo(todo: ToDo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }

    fun updateToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }

    fun doneTodo(id: Int, done: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.doneTodo(id, done)
        }
    }


}