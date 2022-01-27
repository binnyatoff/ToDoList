package ru.binnyatoff.todolist.screens.fragments.list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.viewmodel.ToDoViewModel
import ru.binnyatoff.todolist.room.model.ToDo

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val addbutton: FloatingActionButton = view.findViewById(R.id.addbutton)
        val adapter = ListAdapter()

        //RecyclerView
        adapter.attachDelegate(object : ToDoDelegate {
            override fun todoClick(todo: ToDo) {
                val bundle = bundleOf("currentItem" to todo)
                findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
            }

            override fun todoDelete(todo: ToDo) {
                mToDoViewModel.deleteToDo(todo)
            }

            override fun doneTodo(id: Int, done: Boolean) {
                mToDoViewModel.doneTodo(id, done)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        //Observe data
        mToDoViewModel.readAllData.observe(viewLifecycleOwner, { todo ->
            adapter.setData(todo)
        })

        addbutton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

    }

}