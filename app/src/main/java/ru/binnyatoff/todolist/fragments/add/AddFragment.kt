package ru.binnyatoff.todolist.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.viewmodel.ToDoViewModel
import ru.binnyatoff.todolist.model.ToDo
import ru.binnyatoff.todolist.databinding.FragmentAddBinding

class AddFragment: Fragment(R.layout.fragment_add) {
    lateinit var binding: FragmentAddBinding
    private lateinit var mToDoViewModel: ToDoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

        mToDoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)

        binding.donebutton.setOnClickListener{
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val nameTodo = binding.nameTodo.text.toString()
        val textTodo = binding.textTodo.text.toString()

        if (inputCheck(nameTodo,textTodo)){
            //Create to do object
            val todo = ToDo(0, nameTodo, textTodo, false)
            //Add to Database
            mToDoViewModel.addToDo(todo)
            activity?.onBackPressed()
        }
        else{
            Toast.makeText(requireContext(), "Пожалуйста заполните все строчки",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(nameTodo:String, textTodo:String):Boolean {
        return !(TextUtils.isEmpty(nameTodo) && TextUtils.isEmpty(textTodo))

    }
}