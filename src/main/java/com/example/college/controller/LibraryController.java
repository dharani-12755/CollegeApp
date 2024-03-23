package com.example.college.controller;

import com.example.college.model.Library;
import com.example.college.serviceImpl.LibrarySvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@EnableAutoConfiguration
public class LibraryController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    LibrarySvcImpl librarySvc;

    @PostMapping("/addBooks/")
    public ResponseEntity<Library> addBooks(@RequestBody Library library) {
        logger.info("received request to add a new book");
        Library newBook = librarySvc.addBook(library);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/books/")
    public ResponseEntity<List<Library>> getAllBooks() {
        logger.info("received request to get all the books");
        List<Library> allBooks = librarySvc.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Library> getBook(@PathVariable Long bookId) {
        logger.info("received request to get a book");
        Library book = librarySvc.getBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/books/borrow/{bookId}/{studentId}")
    public ResponseEntity<Library> borrowBook(@PathVariable Long bookId, @PathVariable Long studentId) {
        logger.info("received request to borrow a book to a student");
        Library newBook = librarySvc.addBorrower(bookId, studentId);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @PutMapping("/books/return/{bookId}/")
    public ResponseEntity<Library> returnBook(@PathVariable Long bookId) {
        logger.info("received request to return a book back to library");
        Library newBook = librarySvc.returnBook(bookId);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}/")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        logger.info("received request to delete a book");
        librarySvc.deleteBook(bookId);
        return new ResponseEntity<>("Deleted book successfully", HttpStatus.OK);
    }
}
