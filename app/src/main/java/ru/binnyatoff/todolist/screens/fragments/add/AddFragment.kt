package ru.binnyatoff.todolist.screens.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.databinding.FragmentAddBinding
import ru.binnyatoff.todolist.room.model.ToDo

@AndroidEntryPoint
class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val AddFragmentViewModel: AddFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddBinding.bind(view)

        binding.donebutton.setOnClickListener {
            insertDataToDatabase(binding.nameTodo.text.toString(), binding.textTodo.text.toString())
        }
    }

    private fun insertDataToDatabase(nameTodo: String, textTodo: String) {

        if (inputCheck(nameTodo, textTodo)) {
            //Create to do object
            val todo = ToDo(0,name =  nameTodo, text = textTodo, done = false, 0)
            //Add to Database
            AddFragmentViewModel.addToDo(todo)
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