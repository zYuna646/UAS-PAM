package com.zychan.peminjamanbuku.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.zychan.peminjamanbuku.Control.CntrlBook
import com.zychan.peminjamanbuku.Fragment.displayFragment
import com.zychan.peminjamanbuku.Fragment.inputFragment
import com.zychan.peminjamanbuku.Fragment.removeFragment
import com.zychan.peminjamanbuku.Model.Book
import com.zychan.peminjamanbuku.R
import com.zychan.peminjamanbuku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  lateinit var CntrlBook:CntrlBook
    private  lateinit var books: HashMap<String, Book>

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CntrlBook = CntrlBook()
        books = CntrlBook.getAllBook()
        val gson = Gson()

        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val cntrl = gson.toJson(CntrlBook)
        editor.putString("CtrlBook", cntrl)
        editor.apply()


        val displayFragment = displayFragment()
        val inputFragment = inputFragment()
        val removeFragment = removeFragment()
        replaceFragment(displayFragment)



        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.Buku -> replaceFragment(displayFragment)
                R.id.InputBuku -> replaceFragment(inputFragment)
                R.id.RemoveBuku -> replaceFragment(removeFragment)

                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}