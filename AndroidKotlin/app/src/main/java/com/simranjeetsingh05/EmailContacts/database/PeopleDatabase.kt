package com.simranjeetsingh05.EmailContacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//All entity class goes here
//Singleton because should not create multiple database instances
@Database(entities = [People::class],version = 1)
abstract class PeopleDatabase : RoomDatabase() {

    abstract val peopleDAO: PeopleDAO

    //    Singleton in kotlin - companion
    companion object {

        //        immediate visible to other threads
        @Volatile
        private var INSTANCE: PeopleDatabase? = null
        fun getInstance(context: Context): PeopleDatabase {
            synchronized(this) {
                var instance: PeopleDatabase? = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PeopleDatabase::class.java,
                        "people_databse"
                    ).build()
                }
                return instance
            }

        }

    }
}