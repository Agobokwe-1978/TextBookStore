package com.example.textbookstore.utils

import com.example.textbookstore.models.Book

object DataProvider {

    fun getSampleBooks(): List<Book> = listOf(
        Book(
            id = "1",
            title = "Introduction to Thermodynamics",
            author = "Y.A. Cengel",
            edition = "9th Edition",
            price = 350,
            condition = "Good - Some highlighting",
            courseCode = "ENGG202",
            sellerName = "Thabo Nkosi",
            sellerEmail = "thabo.nkosi@student.ac.za",
            description = "Used for Thermodynamics 101. In good condition with some highlighting in chapter 3. No missing pages."
        ),
        Book(
            id = "2",
            title = "Constitutional Law 101",
            author = "J. Dugard",
            edition = "5th Edition",
            price = 450,
            condition = "Very Good - Like new",
            courseCode = "LAW101",
            sellerName = "Aisha Patel",
            sellerEmail = "aisha.patel@student.ac.za",
            description = "Barely used. Some pencil notes in margins. Perfect for Constitutional Law."
        ),
        Book(
            id = "3",
            title = "Calculus: Early Transcendentals",
            author = "J. Stewart",
            edition = "8th Edition",
            price = 300,
            condition = "Acceptable - Water damage on cover",
            courseCode = "MATH101",
            sellerName = "Sipho Dlamini",
            sellerEmail = "sipho.dlamini@student.ac.za",
            description = "Cover has some water damage but all pages are clean and readable. Great for budget-conscious students."
        ),
        Book(
            id = "4",
            title = "Data Structures and Algorithms",
            author = "C. Cormen",
            edition = "3rd Edition",
            price = 500,
            condition = "Good - No markings",
            courseCode = "CS201",
            sellerName = "Lerato Moloi",
            sellerEmail = "lerato.moloi@student.ac.za",
            description = "Like new condition. No highlighting or notes. Used for Data Structures course."
        ),
        Book(
            id = "5",
            title = "Organic Chemistry",
            author = "P. Bruice",
            edition = "7th Edition",
            price = 400,
            condition = "Fair - Some wear on cover",
            courseCode = "CHEM150",
            sellerName = "Michael van der Merwe",
            sellerEmail = "michael.vdm@student.ac.za",
            description = "Cover shows some wear but inside pages are clean. Essential for Organic Chemistry."
        )
    )
}