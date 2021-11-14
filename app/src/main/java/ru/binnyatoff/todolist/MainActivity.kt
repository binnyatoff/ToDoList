package ru.binnyatoff.todolist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.binnyatoff.todolist.databinding.ActivityMainBinding
import ru.binnyatoff.todolist.fragments.add.AddFragment
import ru.binnyatoff.todolist.fragments.list.ListFragment


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (savedInstanceState == null) {
            if (currentUser != null) {
                supportFragmentManager.commit {
                    replace(R.id.fragment, ListFragment())
                }
            } else
                navController
        }
    }
}

