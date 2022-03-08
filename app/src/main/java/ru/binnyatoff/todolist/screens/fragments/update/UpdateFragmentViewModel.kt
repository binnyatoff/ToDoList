package ru.binnyatoff.todolist.screens.fragments.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.binnyatoff.todolist.room.model.ToDo
import ru.binnyatoff.todolist.room.repository.Repository
import javax.inject.Inject

@HiltViewModel
class UpdateFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun updateToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(todo)
        }
    }
}