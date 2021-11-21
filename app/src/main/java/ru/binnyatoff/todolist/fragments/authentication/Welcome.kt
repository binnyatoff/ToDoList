package ru.binnyatoff.todolist.fragments.authentication

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.databinding.FragmentWelcomeBinding
import ru.binnyatoff.todolist.fragments.list.ListFragment

class Welcome : Fragment(R.layout.fragment_welcome) {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var auth: FirebaseAuth

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