package com.zychan.peminjamanbuku.Interdace

import com.zychan.peminjamanbuku.Model.Book

interface InfBook {
    fun addBook(id: String, title: String, panulis: String, tahunTerbit: String)
    fun getBookById(id: String):Book?
    fun removeBookById(id: String)
    fun getAllBook():HashMap<String, Book>
}