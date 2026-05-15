package com.example.textbookstore.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.textbookstore.R
import com.example.textbookstore.activities.DetailActivity
import com.example.textbookstore.adapters.BookAdapter
import com.example.textbookstore.databinding.FragmentHomeBinding
import com.example.textbookstore.models.Book
import com.example.textbookstore.utils.DataProvider

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: BookAdapter
    private var allBooks = listOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadBooks()
        setupSearch()
    }

    private fun setupRecyclerView() {
        adapter = BookAdapter(emptyList()) { book ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("book_id", book.id)
            startActivity(intent)
        }
        binding.recyclerViewBooks.adapter = adapter
    }

    private fun loadBooks() {
        allBooks = DataProvider.getSampleBooks()
        adapter.updateList(allBooks)
    }

    private fun setupSearch() {
        val editText = binding.layoutSearch.editText
        editText?.doAfterTextChanged { text ->
            filterBooks(text.toString())
        }
    }

    private fun filterBooks(query: String) {
        if (query.isNotEmpty() && query.length < 2) {
            binding.layoutSearch.error = getString(R.string.error_search_too_short)
            return
        } else {
            binding.layoutSearch.error = null
        }

        val filtered = if (query.isEmpty()) {
            allBooks
        } else {
            allBooks.filter { book ->
                book.title.contains(query, ignoreCase = true) ||
                        book.author.contains(query, ignoreCase = true) ||
                        book.courseCode.contains(query, ignoreCase = true)
            }
        }

        adapter.updateList(filtered)

        if (filtered.isEmpty()) {
            binding.layoutEmptyState.visibility = View.VISIBLE
            binding.recyclerViewBooks.visibility = View.GONE
        } else {
            binding.layoutEmptyState.visibility = View.GONE
            binding.recyclerViewBooks.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}