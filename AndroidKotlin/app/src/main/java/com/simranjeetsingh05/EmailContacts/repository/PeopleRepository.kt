package com.simranjeetsingh05.EmailContacts.repository

import com.simranjeetsingh05.EmailContacts.database.People
import com.simranjeetsingh05.EmailContacts.database.PeopleDAO

class PeopleRepository(private val dao:PeopleDAO) {

    val people = dao.getAllPeople()

    suspend fun insert(people: People):Long{
        return dao.insertPeople(people)
    }

    suspend fun update(people: People):Int{
        return dao.updatePeople(people)
    }

    suspend fun delete(people: People):Int{
        return dao.deletePeople(people)
    }

    suspend fun deleteAll():Int{
        return dao.deleteAll()
    }


}