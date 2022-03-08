package ru.binnyatoff.todolist.screens.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.binnyatoff.todolist.room.repository.Repository
import ru.binnyatoff.todolist.room.model.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFragmentViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {
    val readAllData: LiveData<List<ToDo>> = repository.readAllData

    fun deleteToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(todo)
        }
    }

    fun doneTodo(id: Int, done: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.done(id, done)
        }
    }
    fun updateToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(todo)
        }
    }
}