package com.zychan.peminjamanbuku.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.zychan.peminjamanbuku.Control.CntrlBook
import com.zychan.peminjamanbuku.Model.Book
import com.zychan.peminjamanbuku.R

class inputFragment : Fragment() {

    private  lateinit var cntrlBook: CntrlBook
    private  lateinit var books: HashMap<String, Book>

    private lateinit var btnInput: Button
    private lateinit var editId: EditText
    private lateinit var editNamaBuku: EditText
    private lateinit var editPenulis: EditText
    private lateinit var editTahunTerbit: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_input, container, false)


        val gson = Gson()

        val sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val data = sharedPreferences.getString("CtrlBook", "")
        cntrlBook = gson.fromJson(data, com.zychan.peminjamanbuku.Control.CntrlBook::class.java)
        val editor = sharedPreferences.edit()

        btnInput = view.findViewById(R.id.btnInput)
        editId = view.findViewById(R.id.editId)
        editNamaBuku = view.findViewById(R.id.editNamaBuku)
        editPenulis = view.findViewById(R.id.editPenulis)
        editTahunTerbit = view.findViewById(R.id.editTahunTerbit)

        btnInput.setOnClickListener {
            val id: String = editId.text.toString()
            val namaBuku: String = editNamaBuku.text.toString()
            val penulis: String = editPenulis.text.toString()
            val tahunTerbit: String = editTahunTerbit.text.toString()

                    cntrlBook.addBook(id, namaBuku, penulis, tahunTerbit)
                    val value = gson.toJson(cntrlBook)
                    editor.putString("CtrlBook", value)
                    editor.apply()

                    val fragment = displayFragment()
                    val fragmentManager = requireActivity().supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.frame_layout, fragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                    Toast.makeText(requireContext(), "Data Berhasil Di Tambahkan", Toast.LENGTH_SHORT).show()


        }

        return view
    }
}