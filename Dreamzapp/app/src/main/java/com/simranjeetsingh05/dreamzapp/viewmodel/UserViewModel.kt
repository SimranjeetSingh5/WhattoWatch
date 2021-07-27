package com.simranjeetsingh05.dreamzapp.viewmodel

import androidx.lifecycle.ViewModel
import com.simranjeetsingh05.dreamzapp.model.User
import com.simranjeetsingh05.dreamzapp.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel(){

    suspend fun insertUser(user: User) = repository.insertUser(user)

    suspend fun getLoginCred(username: String, password: String):User? = repository.getLoginCred(username,password)
}



