package ru.binnyatoff.todolist.room.data

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.binnyatoff.todolist.room.model.ToDo

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: ToDo)

    @Query("SELECT * FROM todo_data")
    fun readAll(): LiveData<List<ToDo>>

    @Transaction
    fun move(itemlist: List<ToDo>){
    }

    @Delete
    suspend fun delete(todo: ToDo)

    @Update
    suspend fun update(todo: ToDo)

    @Query("UPDATE todo_data SET done= :done WHERE id = :id" )
    suspend fun done(id: Int, done: Boolean)
}

