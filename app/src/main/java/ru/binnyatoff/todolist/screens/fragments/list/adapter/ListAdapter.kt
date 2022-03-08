package ru.binnyatoff.todolist.screens.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.room.model.ToDo
import ru.binnyatoff.todolist.screens.fragments.list.adapter.ListItemTouchHelperAdapter
import ru.binnyatoff.todolist.screens.fragments.list.adapter.ListAdapterDelegate
import ru.binnyatoff.todolist.screens.fragments.list.adapter.ListAdapterDiffUtils
import ru.binnyatoff.todolist.screens.fragments.list.adapter.ViewHolder

class ListAdapter : RecyclerView.Adapter<ViewHolder>(), ListItemTouchHelperAdapter {

    private var todolist = emptyList<ToDo>()
    private var delegate: ListAdapterDelegate? = null


    fun attachDelegate(delegate: ListAdapterDelegate) {
        this.delegate = delegate
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

    override fun onItemDismiss(position: Int) {
        val currentItem = todolist[position]
        delegate?.todoDelete(currentItem)
    }

    fun setData(todo: List<ToDo>) {
        val diffCallback = ListAdapterDiffUtils(todolist, todo)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        todolist = todo
        diffResult.dispatchUpdatesTo(this)
    }
}