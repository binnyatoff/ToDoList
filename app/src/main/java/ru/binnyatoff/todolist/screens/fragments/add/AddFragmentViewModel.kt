package ru.binnyatoff.todolist.screens.fragments.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.binnyatoff.todolist.room.model.ToDo
import ru.binnyatoff.todolist.room.repository.Repository
import javax.inject.Inject

@HiltViewModel
class AddFragmentViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    fun addToDo(todo: ToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }
}