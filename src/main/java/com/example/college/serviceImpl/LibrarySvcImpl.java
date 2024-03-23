package com.example.college.serviceImpl;

import com.example.college.constants.ErrorConstants;
import com.example.college.errors.ApiError;
import com.example.college.errors.EntityNotFoundException;
import com.example.college.model.Events;
import com.example.college.model.Library;
import com.example.college.model.Student;
import com.example.college.repository.LibraryRepo;
import com.example.college.repository.StudentsRepo;
import com.example.college.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class LibrarySvcImpl {

    @Autowired
    LibraryRepo libraryRepo;

    @Autowired
    StudentsSvcImpl studentsSvc;

    public Library addBook(Library library) {
        library.setId(IdGenerator.generateId());
        library.setStatus("available");
        return libraryRepo.save(library);
    }

    public List<Library> getAllBooks() {
        return libraryRepo.findAll();
    }

    public Library getBookById(Long bookId) {
        Optional<Library> book = libraryRepo.findById(bookId);
        if (book.isPresent()) {
            return book.get();
        } else {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                    ErrorConstants.ErrorCode.BOOK_NOT_FOUND.getCode(),
                    ErrorConstants.ErrorCode.BOOK_NOT_FOUND.getMessage());
            throw new EntityNotFoundException(apiError, bookId);
        }
    }

    public Library addBorrower(Long bookId, Long studentId) {
        Student student = studentsSvc.getStudent(studentId);
        Library library = getBookById(bookId);
        if (library.getStatus().equals("borrowed")) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                    ErrorConstants.ErrorCode.BOOK_ALREADY_BORROWED.getCode(),
                    ErrorConstants.ErrorCode.BOOK_ALREADY_BORROWED.getMessage());
            throw new EntityNotFoundException(apiError, bookId);
        } else {
            library.setCheckoutDate(Instant.now());
            library.setBorrowerName(student.getName());
            library.setStatus("borrowed");
            library.setReturned(false);
            library.setDueDate();
        }
        return libraryRepo.save(library);
    }

    public Library returnBook(Long bookId) {
        Library library = getBookById(bookId);
        if (library.getStatus().equals("active")) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                    ErrorConstants.ErrorCode.BOOK_ALREADY_RETURNED.getCode(),
                    ErrorConstants.ErrorCode.BOOK_ALREADY_RETURNED.getMessage());
            throw new EntityNotFoundException(apiError, bookId);
        } else {
            library.setBorrowerName(null);
            library.setStatus("active");
            library.setReturned(true);
            library.setDueDate(null);
            library.setFineAmount();
        }
        return libraryRepo.save(library);
    }

    public void deleteBook(Long bookId) {
        libraryRepo.deleteById(bookId);
    }
}
