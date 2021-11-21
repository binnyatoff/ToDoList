package ru.binnyatoff.todolist.fragments.authentication

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
import ru.binnyatoff.todolist.databinding.FragmentSigninBinding

class SignIn : Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSigninBinding.bind(view)
        val email = binding.signinLoginText.text
        val password = binding.signinPasswordText.text
        binding.signin.setOnClickListener{
            signin(email.toString(),password.toString())
        }

    }

    fun signin(email: String, password: String) {
        auth = Firebase.auth
        activity?.let {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val user = auth.currentUser
                        Log.d("TAG", user.toString())
                        findNavController().navigate(R.id.action_signIn_to_listFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("TAG", "signInWithEmail:failure")
                        Toast.makeText(
                            context, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}