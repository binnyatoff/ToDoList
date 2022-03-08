package ru.binnyatoff.todolist.room.model


import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todo_data")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "done")
    val done: Boolean,
    @ColumnInfo(name = "position")
    val position: Int
) : Parcelable

