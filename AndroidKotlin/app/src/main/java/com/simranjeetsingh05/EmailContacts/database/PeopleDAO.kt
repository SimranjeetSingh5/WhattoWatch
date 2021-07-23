package com.simranjeetsingh05.EmailContacts.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeopleDAO {

    @Insert
    suspend fun insertPeople(people: People): Long

    @Update
    suspend fun updatePeople(people: People): Int

    @Delete
    suspend fun deletePeople(people: People) : Int

    //Verified at compile time by room
    @Query("DELETE FROM people_data_table")
    suspend fun deleteAll(): Int

    //LivaData from async query runs on background thread
    //Automatically on suspend
    @Query("SELECT * FROM people_data_table")
    fun getAllPeople():LiveData<List<People>>
}