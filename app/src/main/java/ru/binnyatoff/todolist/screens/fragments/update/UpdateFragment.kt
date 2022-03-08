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

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.fragment_update) {
    private val mUpdateFragmentViewModel: UpdateFragmentViewModel by viewModels()
    private var binding:FragmentUpdateBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        val currentItem = arguments?.getParcelable<ToDo>("currentItem")
        binding?.updateNameTodo?.setText(currentItem?.name)
        binding?.updateTextTodo?.setText(currentItem?.text)

        binding?.updateButton?.setOnClickListener {
            if (currentItem != null) {
                val nameToDo = binding?.updateNameTodo?.text.toString()
                val textTodo = binding?.updateTextTodo?.text.toString()
                updateDatabase(currentItem, nameToDo, textTodo)
            }
        }
    }

    private fun updateDatabase(currentItem: ToDo, nameTodo: String, textTodo: String) {

        val todo = ToDo(currentItem.id, nameTodo, textTodo, currentItem.done, currentItem.position)

        if (inputCheck(nameTodo, textTodo)) {
            mUpdateFragmentViewModel.updateToDo(todo)
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

