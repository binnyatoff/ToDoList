package ru.binnyatoff.todolist.model


import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "todo_data")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name_todo")
    val name_todo: String,
    @ColumnInfo(name = "text_todo")
    val text_todo: String,
    @ColumnInfo(name = "done_todo")
    val done_todo: Boolean
) : Parcelable

