package com.simranjeetsingh05.dreamzapp.repository

import com.simranjeetsingh05.dreamzapp.database.UserDatabase
import com.simranjeetsingh05.dreamzapp.model.User

class UserRepository(private val userDatabase: UserDatabase) {

     suspend fun insertUser(user: User) = userDatabase.userDao().insertUser(user)

     suspend fun getLoginCred(username:String,password:String) :User?= userDatabase.userDao().getLoginCred(username,password)

}