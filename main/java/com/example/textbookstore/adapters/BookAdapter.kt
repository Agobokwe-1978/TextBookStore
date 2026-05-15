package com.example.textbookstore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.textbookstore.R
import com.example.textbookstore.models.Book

class BookAdapter(
    private var books: List<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
    ) {
        private val imageCover: ImageView = itemView.findViewById(R.id.image_book_cover)
        private val textTitle: TextView = itemView.findViewById(R.id.text_book_title)
        private val textAuthor: TextView = itemView.findViewById(R.id.text_book_author)
        private val textPrice: TextView = itemView.findViewById(R.id.text_book_price)
        private val textCondition: TextView = itemView.findViewById(R.id.text_condition_badge)

        fun bind(book: Book) {
            textTitle.text = book.title
            textAuthor.text = "By ${book.author}"
            textPrice.text = "R${book.price}"
            textCondition.text = book.condition

            imageCover.setImageResource(R.drawable.ic_book_placeholder)

            itemView.setOnClickListener { onItemClick(book) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount() = books.size

    fun updateList(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}