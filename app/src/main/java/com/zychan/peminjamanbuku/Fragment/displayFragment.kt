package com.zychan.peminjamanbuku.Fragment

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.zychan.peminjamanbuku.Control.CntrlBook
import com.zychan.peminjamanbuku.Model.Book
import com.zychan.peminjamanbuku.R


class displayFragment : Fragment() {

    private lateinit var listBooks: LinearLayout
    private lateinit var cntrlBook: CntrlBook
    private lateinit var books: HashMap<String, Book>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        val gson = Gson()

        val sharedPreferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val data = sharedPreferences.getString("CtrlBook", "")

        cntrlBook = gson.fromJson(data, CntrlBook::class.java)
        books = cntrlBook.getAllBook()
        listBooks = view.findViewById(R.id.listBooks)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        listBooks.removeAllViews()
        for ((key, value) in books) {
            val textId = TextView(requireContext())
            val textTitle = TextView(requireContext())
            val textPenulis = TextView(requireContext())
            val textTahunTerbit = TextView(requireContext())

            textId.text = "id buku : ${value.id}, "
            textTitle.text = "nama buku : ${value.title}"
            textPenulis.text = "penulis : ${value.penulis}"
            textTahunTerbit.text = "tahun terbit : ${value.tahunTerbit}"

            listBooks.addView(textId, layoutParams)
            listBooks.addView(textTitle, layoutParams)
            listBooks.addView(textPenulis, layoutParams)
            listBooks.addView(textTahunTerbit, layoutParams)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(
            0,
            5,
            0,
            16
        )

        listBooks.removeAllViews()
        for ((key, value) in books) {
            val textId = TextView(requireContext())
            val textTitle = TextView(requireContext())
            val textPenulis = TextView(requireContext())
            val textTahunTerbit = TextView(requireContext())

            textId.apply {
                text = "ID Buku: ${value.id}"
                textSize = 16f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                background = createTextViewBackground()
            }

            textTitle.apply {
                text = "Nama Buku: ${value.title}"
                textSize = 18f
                setTypeface(null, Typeface.BOLD)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                background = createTextViewBackground()
            }

            textPenulis.apply {
                text = "Penulis: ${value.penulis}"
                textSize = 14f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                background = createTextViewBackground()
            }

            textTahunTerbit.apply {
                text = "Tahun Terbit: ${value.tahunTerbit}"
                textSize = 14f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
                background = createTextViewBackground()
            }

            listBooks.addView(textId, layoutParams)
            listBooks.addView(textTitle, layoutParams)
            listBooks.addView(textPenulis, layoutParams)
            listBooks.addView(textTahunTerbit, layoutParams)
        }
    }

    private fun createTextViewBackground(): Drawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = resources.getDimension(R.dimen.corner_radius)
        shape.setColor(ContextCompat.getColor(requireContext(), R.color.text_background_color))
        return shape
    }
}