package ru.binnyatoff.todolist.screens.fragments.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.databinding.FragmentListBinding
import ru.binnyatoff.todolist.room.model.ToDo
import ru.binnyatoff.todolist.screens.fragments.list.adapter.ListItemTouchHelperCallback
import ru.binnyatoff.todolist.screens.fragments.list.adapter.ListAdapterDelegate

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private val mListFragmentViewModel: ListFragmentViewModel by viewModels()
    private var binding: FragmentListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        val adapter = ListAdapter()
        val recyclerView = binding?.recyclerview
        val callback = ListItemTouchHelperCallback(adapter)
        val touchHelper = androidx.recyclerview.widget.ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recyclerView)

        //RecyclerView
        adapter.attachDelegate(object : ListAdapterDelegate {
            override fun todoClick(todo: ToDo) {
                val bundle = bundleOf("currentItem" to todo)
                findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
            }

            override fun todoDelete(todo: ToDo) {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                mListFragmentViewModel.deleteToDo(todo)
                val newtodo = ToDo(todo.id, todo.name,todo.text,todo.done,todo.position)
                mListFragmentViewModel.updateToDo(newtodo)
            }

            override fun doneTodo(id: Int, done: Boolean) {
                mListFragmentViewModel.doneTodo(id, done)
            }
        })

        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())

        //Observe data
        mListFragmentViewModel.readAllData.observe(viewLifecycleOwner) { todo ->
            adapter.setData(todo)
        }

        binding?.addbutton?.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }
}
