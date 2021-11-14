package ru.binnyatoff.todolist.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.model.ToDo

interface ToDoActionListener {
    fun todoDelete(todo: ToDo)
    fun doneTodo(id: Int, done: Boolean)
}

class ListAdapter(private val actionListener: ToDoActionListener) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var todolist = emptyList<ToDo>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todo_item: View = itemView.findViewById(R.id.todo_item)
        var name_todo: TextView = itemView.findViewById(R.id.name_todo)
        var text_todo: TextView = itemView.findViewById(R.id.text_todo)
        var delete_todo: ImageButton = itemView.findViewById(R.id.delete_todo)
        var done_todo: CheckBox = itemView.findViewById(R.id.done_todo) as CheckBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun getItemCount(): Int = todolist.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curentItem = todolist[position]
        holder.name_todo.text = curentItem.name_todo
        holder.text_todo.text = curentItem.text_todo
        holder.done_todo.isChecked = curentItem.done_todo

        holder.todo_item.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(curentItem)
            holder.itemView.findNavController().navigate(action)

        }

        holder.delete_todo.setOnClickListener {
            actionListener.todoDelete(curentItem)
        }
        holder.done_todo.setOnClickListener {
            actionListener.doneTodo(curentItem.id, holder.done_todo.isChecked)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(todo: List<ToDo>) {
        todolist = todo
        notifyDataSetChanged()
    }

}