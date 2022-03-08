package ru.binnyatoff.todolist.screens.fragments.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        binding.welcomeRegister.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_register)
        }
        binding.welcomeSignin.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_signIn)
        }
    }
}