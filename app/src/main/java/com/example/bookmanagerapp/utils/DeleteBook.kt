package com.example.bookmanagerapp.utils

import com.example.bookmanagerapp.model.BooksInfo

interface DeleteBook {
    //interface for deleting the book
    fun onDelete(booksInfo: BooksInfo)
}