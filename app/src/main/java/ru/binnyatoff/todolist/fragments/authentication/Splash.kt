package ru.binnyatoff.todolist.fragments.authentication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.binnyatoff.todolist.R

class Splash: Fragment(R.layout.fragment_splash) {
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_splash_to_listFragment)
        }
        else{
            findNavController().navigate(R.id.action_splash_to_welcome)
        }
    }
}