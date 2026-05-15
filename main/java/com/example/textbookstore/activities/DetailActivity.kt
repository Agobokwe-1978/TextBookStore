package com.example.textbookstore.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.textbookstore.R
import com.example.textbookstore.models.Book
import com.example.textbookstore.utils.DataProvider

class DetailActivity : AppCompatActivity() {

    private lateinit var currentBook: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar()
        loadBookData()
        setupInquiryButton()
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadBookData() {
        val bookId = intent.getStringExtra("book_id")
        currentBook = DataProvider.getSampleBooks().find { it.id == bookId }
            ?: DataProvider.getSampleBooks()[0]

        findViewById<TextView>(R.id.text_book_title).text = currentBook.title
        findViewById<TextView>(R.id.text_book_author).text = "By ${currentBook.author}"
        findViewById<TextView>(R.id.text_book_price).text = "R${currentBook.price}"
        findViewById<TextView>(R.id.text_book_edition).text = currentBook.edition
        findViewById<TextView>(R.id.text_condition).text = currentBook.condition
        findViewById<TextView>(R.id.text_course_code).text = currentBook.courseCode
        findViewById<TextView>(R.id.text_seller).text = currentBook.sellerName
        findViewById<TextView>(R.id.text_description).text = currentBook.description
    }

    private fun setupInquiryButton() {
        val buttonInquire = findViewById<Button>(R.id.button_inquire)
        buttonInquire.setOnClickListener {
            showInquiryDialog()
        }
    }

    private fun showInquiryDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_inquiry_title))
            .setMessage(String.format(getString(R.string.dialog_inquiry_message), currentBook.title))
            .setPositiveButton(getString(R.string.action_send)) { _, _ ->
                sendEmailInquiry()
            }
            .setNegativeButton(getString(R.string.action_cancel), null)
            .show()
    }

    private fun sendEmailInquiry() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${currentBook.sellerEmail}")
            putExtra(Intent.EXTRA_SUBJECT, "Inquiry about ${currentBook.title}")
            putExtra(Intent.EXTRA_TEXT, buildEmailBody())
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Send Email"))
            Toast.makeText(this, R.string.toast_email_sent, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buildEmailBody(): String {
        return """
            Hello ${currentBook.sellerName},
            
            I'm interested in your textbook: ${currentBook.title}
            Price: R${currentBook.price}
            Condition: ${currentBook.condition}
            
            Is this still available?
            
            Regards,
            [Your Name]
            [Your Student ID]
            
        """.trimIndent()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}