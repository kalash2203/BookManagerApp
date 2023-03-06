package com.example.bookmanagerapp.db

import androidx.room.*
import com.example.bookmanagerapp.model.BooksInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    //call coroutine using launch function to insert book in database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(booksInfo: BooksInfo)

    //call coroutine using launch function to delete book in database
    @Delete
    suspend fun deleteBook(booksInfo: BooksInfo)

    @Query("SELECT * FROM todo ORDER BY Name ASC ")
    fun getAllBooks(): Flow<List<BooksInfo>>
    //Run Query to Select All Columns and Order them according to User Name in ascending order

    @Update
    suspend fun update(booksInfo: BooksInfo)
    //call coroutine using launch function to update book in database


}