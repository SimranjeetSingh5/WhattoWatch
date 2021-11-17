package com.simranjeetsingh05.dreamzapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simranjeetsingh05.dreamzapp.model.User

@Dao
interface UserDao {

    //Inserts one user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User?)

    @Query("SELECT * FROM user_data_table WHERE username =:username AND password=:password")
    suspend fun getLoginCred(username: String, password: String):User?

}