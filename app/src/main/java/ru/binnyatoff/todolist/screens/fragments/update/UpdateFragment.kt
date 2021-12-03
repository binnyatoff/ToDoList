package ru.binnyatoff.todolist.screens.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.room.model.ToDo
import ru.binnyatoff.todolist.databinding.FragmentUpdateBinding
import ru.binnyatoff.todolist.viewmodel.ToDoViewModel

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.fragment_update) {

    lateinit var binding: FragmentUpdateBinding
    private val mToDoViewModel:ToDoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentItem = arguments?.getParcelable<ToDo>("currentItem")

        binding = FragmentUpdateBinding.bind(view)

        binding.updateNameTodo.setText(currentItem?.name_todo)
        binding.updateTextTodo.setText(currentItem?.text_todo)

        binding.updateButton.setOnClickListener { updateDatabase(currentItem) }

    }

    private fun updateDatabase(currentItem: ToDo?) {
        val nameTodo = binding.updateNameTodo.text.toString()
        val textTodo = binding.updateTextTodo.text.toString()
        val todo = currentItem?.let { ToDo(it.id, nameTodo, textTodo, currentItem.done_todo) }

        if (inputCheck(nameTodo, textTodo)) {
            if (todo != null) {
                mToDoViewModel.updateToDo(todo)
            }
            activity?.onBackPressed()
        } else {
            Toast.makeText(requireContext(), "Пожалуйста заполните все строчки", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(nameTodo: String, textTodo: String): Boolean {
        return !(TextUtils.isEmpty(nameTodo) && TextUtils.isEmpty(textTodo))

    }

}
