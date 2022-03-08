package ru.binnyatoff.todolist.screens.fragments.authentication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.binnyatoff.todolist.R
import ru.binnyatoff.todolist.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private var binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)


        binding?.signUp?.setOnClickListener {
            val email = binding?.registerLoginText?.text
            val password = binding?.registerPasswordText?.text
            register(email.toString(), password.toString())
        }
    }

    private fun register(email: String, password: String) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    Log.d("TAG", user.toString())
                    findNavController().navigate(R.id.action_register_to_listFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("TAG", "createUserWithEmail:failure $email $password")
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
