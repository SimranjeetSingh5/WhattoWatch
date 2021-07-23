package com.simranjeetsingh05.EmailContacts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people_data_table")
data class People (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "people_id")
    var id : Int,

    @ColumnInfo(name = "people_name")
    var name : String,

    @ColumnInfo(name = "people_email")
    var email : String){

}