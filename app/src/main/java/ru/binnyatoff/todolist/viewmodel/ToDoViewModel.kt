package ru.binnyatoff.todolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.binnyatoff.todolist.room.repository.ToDoRepository
import ru.binnyatoff.todolist.room.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(private val repository: ToDoRepository) : ViewModel() {
    val readAllData: LiveData<List<ToDo>> = repository.readAllData

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