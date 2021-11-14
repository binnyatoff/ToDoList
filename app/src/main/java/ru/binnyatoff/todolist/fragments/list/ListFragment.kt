package ru.binnyatoff.todolist.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.viewmodel.ToDoViewModel
import ru.binnyatoff.todolist.model.ToDo
import ru.binnyatoff.todolist.databinding.FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list) {
    lateinit var binding: FragmentListBinding
    private lateinit var mToDoViewModel: ToDoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        mToDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        //RecyclerView
        val adapter = ListAdapter(object : ToDoActionListener {
            override fun todoDelete(todo: ToDo) {
                mToDoViewModel.deleteToDo(todo)
            }

            override fun doneTodo(id: Int, done: Boolean) {
                mToDoViewModel.doneTodo(id, done)
            }
        })

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())


        //Observe data
        mToDoViewModel.readAllData.observe(viewLifecycleOwner, { todo ->
            adapter.setData(todo)
        })

        binding.addbutton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}