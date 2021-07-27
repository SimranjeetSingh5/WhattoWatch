package com.simranjeetsingh05.dreamzapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simranjeetsingh05.dreamzapp.repository.UserRepository
import java.lang.Exception

class UserViewModelFactory (
    private val repository: UserRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(UserRepository::class.java)
            return constructor.newInstance(repository)
        }catch (e:Exception) {
            Log.e("Error",e.message.toString())
        }
        return super.create(modelClass)
    }
}