package com.simranjeetsingh05.EmailContacts.viewmodel

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simranjeetsingh05.EmailContacts.Event
import com.simranjeetsingh05.EmailContacts.database.People
import com.simranjeetsingh05.EmailContacts.repository.PeopleRepository
import kotlinx.coroutines.launch

class PeopleViewModel(private val repository: PeopleRepository) : ViewModel(),Observable {

    val people = repository.people
    private var isUpdateOrDelete = false
    private lateinit var peopleToUpdateOrDelete : People

    @Bindable
    val inputName = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText  = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {

        if (inputName.value == null) {
            statusMessage.value = Event("Please enter people name!!")
        } else if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter people email!!")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
            statusMessage.value = Event("Please enter a correct email address!!")
        } else {
            if (isUpdateOrDelete) {
                peopleToUpdateOrDelete.name = inputName.value!!
                peopleToUpdateOrDelete.email = inputEmail.value!!
                update(peopleToUpdateOrDelete)
            } else {
                //        !! is used for null safety
                val name = inputName.value!!
                val email = inputEmail.value!!
                insert(People(0, name, email))
                inputName.value = null
                inputEmail.value = null
            }
        }
    }


    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(peopleToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun insert(people: People) = viewModelScope.launch {
            val newRowId = repository.insert(people)
            if(newRowId>-1){
                statusMessage.value = Event("${newRowId} Subscriber Inserted SuccessFully")
            }else{
                statusMessage.value = Event("Error Occured")
            }
    }

    fun update(people: People) = viewModelScope.launch {
        val noOfRows = repository.update(people)
        if(noOfRows>0) {
            repository.update(people)
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "ClearAll"
            statusMessage.value = Event("${noOfRows} Row updated Successfully!!")
        }
        else{
            statusMessage.value = Event("Error occured!!")

        }

    }

    fun delete(people: People) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(people)
        if(noOfRowsDeleted>0) {
            repository.delete(people)
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "ClearAll"
            statusMessage.value = Event("$noOfRowsDeleted deleted Successfully!!")
        }else{
            statusMessage.value = Event("Error Occured")
        }
    }

    private fun clearAll() = viewModelScope.launch {
        val noOfAllDeleted = repository.deleteAll()
        if(noOfAllDeleted>0){
        repository.deleteAll()
        statusMessage.value = Event(" $noOfAllDeleted People Deleted Successfully!!")}
        else{
            statusMessage.value = Event(" Error Occured!!")
        }

    }
    fun initUpdateAndDelete(people: People){
        inputName.value = people.name
        inputEmail.value  = people.email
        isUpdateOrDelete = true
        peopleToUpdateOrDelete = people
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}