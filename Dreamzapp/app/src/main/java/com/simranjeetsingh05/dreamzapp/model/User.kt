package com.simranjeetsingh05.dreamzapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "userId") var uid: Int? = null,
        @ColumnInfo(name = "first_name") var firstName: String?,
        @ColumnInfo(name = "last_name") var lastName: String?,
        @ColumnInfo(name = "image" ,typeAffinity = ColumnInfo.BLOB)  var image:ByteArray? = null,
        @ColumnInfo(name = "date_of_birth") var dob: String?,
        @ColumnInfo(name = "username") var username: String?,
        @ColumnInfo(name = "password") var password: String?,

        ) {

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as User

                if (!image.contentEquals(other.image)) return false

                return true
        }

        override fun hashCode(): Int {
                return image?.contentHashCode() ?: 0
        }

}