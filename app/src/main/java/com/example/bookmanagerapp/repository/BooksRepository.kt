package com.example.bookmanagerapp.repository

import com.example.bookmanagerapp.db.BooksDao
import com.example.bookmanagerapp.model.BooksInfo
import javax.inject.Inject


class BooksRepository
@Inject
constructor(private val booksDao: BooksDao) {

    suspend fun insertBook(booksInfo: BooksInfo) = booksDao.insertBook(booksInfo)
    suspend fun deleteBook(booksInfo: BooksInfo) = booksDao.deleteBook(booksInfo)
    suspend fun updateBook(booksInfo: BooksInfo) = booksDao.update(booksInfo)
    fun getAllBooks() = booksDao.getAllBooks()

}