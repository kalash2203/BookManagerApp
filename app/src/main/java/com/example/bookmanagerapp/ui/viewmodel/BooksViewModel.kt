package com.example.bookmanagerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookmanagerapp.model.BooksInfo
import com.example.bookmanagerapp.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//Injected Repository Class
@HiltViewModel
class BooksViewModel
@Inject
constructor(private val booksRepository: BooksRepository) : ViewModel() {

    //call coroutine using launch function to insert book in database
    fun insertBook(booksInfo: BooksInfo) = viewModelScope.launch {
        booksRepository.insertBook(booksInfo)
    }

    //call coroutine using launch function to delete book in database
    fun deleteBook(booksInfo: BooksInfo) = viewModelScope.launch {
        booksRepository.deleteBook(booksInfo)
    }
    //call coroutine using launch function to update book in database
    fun updateBook(booksInfo: BooksInfo) = viewModelScope.launch {
        booksRepository.updateBook(booksInfo)
    }

    //Update List everytime whenever there is change in data
    val allBooks = booksRepository.getAllBooks().asLiveData()

}
