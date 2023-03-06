package com.example.bookmanagerapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Table named book to store all the records like id , name , Number , Book Name
@Entity(tableName = "todo")
data class BooksInfo(
    //id is auto generated and used as primary Key
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Name: String,
    val Number:String,
    val BookName:String
)
