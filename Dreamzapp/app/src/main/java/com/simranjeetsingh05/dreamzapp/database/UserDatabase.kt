package com.simranjeetsingh05.dreamzapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.simranjeetsingh05.dreamzapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class], version = 1,exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): UserDatabase {
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(
                    ctx.applicationContext, UserDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            INSTANCE = instance
        }
            return instance

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(INSTANCE!!)
            }
        }

        private fun populateDatabase(db: UserDatabase) {
            val noteDao = db.userDao()
            val arr = byteArrayOfInts(0xA1, 0x2E, 0x38, 0xD4, 0x89, 0xC3)
            CoroutineScope(Dispatchers.IO).launch {  noteDao.insertUser(User(1,"Simranjeet","Singh",arr,"yoyo","simranjeet","pass"))
            noteDao.insertUser(User(2,"Simran","jeet",arr,"yo","simranjeet12","word"))
        }}
        fun byteArrayOfInts(vararg ints: Int) = ByteArray(ints.size) { pos -> ints[pos].toByte() }
    }
}
