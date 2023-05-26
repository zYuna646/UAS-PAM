package com.zychan.peminjamanbuku.Control

import com.zychan.peminjamanbuku.Interdace.InfBook
import com.zychan.peminjamanbuku.Model.Book

class CntrlBook : InfBook {

    private val books = HashMap<String, Book>()


    override fun addBook(id: String, title: String, penulis: String, tahunTerbit: String) {
        books[id] = Book(id, title, penulis, tahunTerbit)
    }

    override fun getBookById(id: String): Book? {
        if(books[id] != null)
        {
            return books[id]
        }
        return null
    }

    override fun removeBookById(id: String) {
        books.remove(id)
    }

    override fun getAllBook(): HashMap<String, Book> {
        return this.books
    }
}