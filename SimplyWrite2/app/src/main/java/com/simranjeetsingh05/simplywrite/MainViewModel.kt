package com.simranjeetsingh05.simplywrite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.simranjeetsingh05.simplywrite.data.AppDatabase
import com.simranjeetsingh05.simplywrite.data.NoteEntity
import com.simranjeetsingh05.simplywrite.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)
    val noteList = database?.noteDao()?.getAll()

    fun addSampleData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val sampleNotes = SampleDataProvider.getNotes()
                database?.noteDao()?.insertAll(sampleNotes)
            }
        }
    }

    fun deleteNotes(selectedNotes: List<NoteEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database?.noteDao()?.deleteNotes(selectedNotes)
            }
        }

    }
    fun deleteAllNotes(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database?.noteDao()?.deleteAll()
            }
        }
    }

}