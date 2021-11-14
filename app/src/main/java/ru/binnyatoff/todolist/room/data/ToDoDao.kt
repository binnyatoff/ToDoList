package ru.binnyatoff.todolist.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.binnyatoff.todolist.model.ToDo

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: ToDo)

    @Query("SELECT * FROM todo_data ORDER BY id ASC")
    fun readAllData(): LiveData<List<ToDo>>

    @Delete
    suspend fun deleteTodo(todo: ToDo)

    @Update
    suspend fun updateTodo(todo: ToDo)

    @Query("UPDATE todo_data SET done_todo= :done WHERE id = :id" )
    suspend fun doneTodo(id: Int, done: Boolean)
}

