package ru.binnyatoff.todolist.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.model.ToDo

interface ToDoDelegate {
    fun todoClick(todo: ToDo)
    fun todoDelete(todo: ToDo)
    fun doneTodo(id: Int, done: Boolean)
}

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var todolist = emptyList<ToDo>()
    private var delegate: ToDoDelegate? = null


    fun attachDelegate(delegate: ToDoDelegate) {
        this.delegate = delegate
    }

    class ViewHolder(itemView: View, private val delegate: ToDoDelegate?) :
        RecyclerView.ViewHolder(itemView) {

        val todo_item: View = itemView.findViewById(R.id.todo_item)
        var name_todo: TextView = itemView.findViewById(R.id.name_todo)
        var text_todo: TextView = itemView.findViewById(R.id.text_todo)
        var delete_todo: ImageButton = itemView.findViewById(R.id.delete_todo)
        var done_todo: CheckBox = itemView.findViewById(R.id.done_todo) as CheckBox

        fun bind(item: ToDo) {
            name_todo.text = item.name_todo
            text_todo.text = item.text_todo
            done_todo.isChecked = item.done_todo

            delete_todo.setOnClickListener {
                delegate?.todoDelete(item)
            }
            done_todo.setOnClickListener {
                delegate?.doneTodo(item.id, done_todo.isChecked)
            }
            todo_item.setOnClickListener {
                delegate?.todoClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view, delegate)
    }

    override fun getItemCount(): Int = todolist.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = todolist[position]
        holder.bind(currentItem)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(todo: List<ToDo>) {
        todolist = todo
        notifyDataSetChanged()
    }

}