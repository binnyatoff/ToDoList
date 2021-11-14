package ru.binnyatoff.todolist.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.model.ToDo
import ru.binnyatoff.todolist.databinding.FragmentUpdateBinding
import ru.binnyatoff.todolist.viewmodel.ToDoViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var binding: FragmentUpdateBinding
    lateinit var mToDoViewModel: ToDoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)

        binding.updateNameTodo.setText(args.curentItem.name_todo)
        binding.updateTextTodo.setText(args.curentItem.text_todo)

        mToDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        binding.updateButton.setOnClickListener {updateDatabase()}

    }

    private fun updateDatabase() {
        val nameTodo = binding.updateNameTodo.text.toString()
        val textTodo = binding.updateTextTodo.text.toString()
        val todo = ToDo(args.curentItem.id, nameTodo, textTodo, args.curentItem.done_todo)
        if (inputCheck(nameTodo, textTodo)) {
            mToDoViewModel.updateToDo(todo)
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

