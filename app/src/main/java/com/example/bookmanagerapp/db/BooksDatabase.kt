package com.example.bookmanagerapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookmanagerapp.model.BooksInfo

@Database(
    entities = [BooksInfo::class],
    version = 3, exportSchema = false
)
abstract class BooksDatabase : RoomDatabase() {
//Extend Room Database
    abstract fun booksDao(): BooksDao

}