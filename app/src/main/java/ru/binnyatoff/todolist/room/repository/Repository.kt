package ru.binnyatoff.todolist.room.repository

import androidx.lifecycle.LiveData
import ru.binnyatoff.todolist.room.data.RoomDao
import ru.binnyatoff.todolist.room.model.ToDo
import javax.inject.Inject

class Repository @Inject constructor(private var roomDao: RoomDao) {
    val readAllData:LiveData<List<ToDo>> = roomDao.readAll()

    suspend fun insert(todo: ToDo){
        roomDao.insert(todo)
    }

    suspend fun delete(todo: ToDo){
        roomDao.delete(todo)
    }

    suspend fun update(todo: ToDo) {
        roomDao.update(todo)
    }
    suspend fun done(id: Int, done: Boolean){
        roomDao.done(id, done)
    }

    suspend fun move(todolist: List<ToDo>){
        roomDao.move(todolist)
    }
}