package ru.binnyatoff.todolist.screens.fragments.list.adapter

interface ListItemTouchHelperAdapter {
    //fun onItemMove(fromPosition: Int, toPosition: Int):Boolean
    fun onItemDismiss(position: Int)
}