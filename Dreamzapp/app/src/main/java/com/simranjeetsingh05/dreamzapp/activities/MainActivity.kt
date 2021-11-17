package com.simranjeetsingh05.dreamzapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.simranjeetsingh05.dreamzapp.R
import com.simranjeetsingh05.dreamzapp.database.UserDatabase
import com.simranjeetsingh05.dreamzapp.databinding.ActivityMainBinding
import com.simranjeetsingh05.dreamzapp.model.User
import com.simranjeetsingh05.dreamzapp.repository.UserRepository
import com.simranjeetsingh05.dreamzapp.viewmodel.UserViewModel
import com.simranjeetsingh05.dreamzapp.viewmodel.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    var userName: String? = null
    var passWord: String? = null

    private lateinit var viewModel: UserViewModel
    private lateinit var userDatabase: UserDatabase
    private lateinit var repository: UserRepository
    private lateinit var factory: UserViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDatabase = UserDatabase.getInstance(this)
        repository = UserRepository(userDatabase)
        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        userName = binding.username.text.toString()
        passWord = binding.password.text.toString()



        binding.signUpButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                intent.putExtra("actionBarText", "Create Account")
                startActivity(intent)
            }
        }
        binding.loginButton.setOnClickListener {

            if (validateLogin(userName!!, passWord!!)) {
                CoroutineScope(Dispatchers.IO).launch {
                    val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                    intent.putExtra("actionBarText", "Update Account")
                    startActivity(intent)
                }
            } else {
                Toast.makeText(applicationContext, "User Does not exist", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun validateLogin(userName: String, passWord: String): Boolean {
//        Validate login with already present data in database
        var flag = true
        CoroutineScope(Dispatchers.IO).launch {
            val user: User? = viewModel.getLoginCred(userName,passWord)
            if(user!=null){
                val toast = Toast.makeText(
                    applicationContext!!, "Account Created : $userName $passWord",
                    Toast.LENGTH_LONG
                )
                toast.show()
                flag = true
            }
        }
        return flag

    }

}

