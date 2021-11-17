package com.simranjeetsingh05.EmailContacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simranjeetsingh05.EmailContacts.repository.PeopleRepository
import java.lang.IllegalArgumentException

class PeopleViewModelFactory(private val repository: PeopleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom((PeopleViewModel::class.java))){
            return PeopleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View model class")
    }

}