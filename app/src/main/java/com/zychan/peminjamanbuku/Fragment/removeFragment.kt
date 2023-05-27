package com.zychan.peminjamanbuku.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.zychan.peminjamanbuku.Control.CntrlBook
import com.zychan.peminjamanbuku.R


class removeFragment : Fragment() {

    private lateinit var btnRemove: Button
    private lateinit var editId: EditText
    private lateinit var CntrlBook:CntrlBook


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val data = sharedPreferences.getString("CtrlBook","")
        CntrlBook = gson.fromJson(data, com.zychan.peminjamanbuku.Control.CntrlBook::class.java)

        val editor = sharedPreferences.edit()
        val view = inflater.inflate(R.layout.fragment_remove, container, false)
        editId = view.findViewById(R.id.editId)
        btnRemove = view.findViewById(R.id.btnRemove)
        btnRemove.setOnClickListener{
            val id: String = editId.text.toString()
            CntrlBook.removeBookById(id)
            val Value = gson.toJson(CntrlBook)
            editor.putString("CtrlBook", Value)
            editor.apply()
            val fragment = displayFragment() // Replace `AnotherFragment()` with the fragment you want to navigate to
            val fragmentManager = requireActivity().supportFragmentManager // Use `childFragmentManager` if you are inside a nested fragment
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, fragment) // Replace `R.id.fragmentContainer` with the ID of the container where you want to replace the fragment
            fragmentTransaction.addToBackStack(null) // Add the transaction to the back stack, so the user can navigate back
            fragmentTransaction.commit()
            Toast.makeText(requireContext(), "Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show()
        }
        return view
    }

}