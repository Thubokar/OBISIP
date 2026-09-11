package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllBooks();
        }

        String search = keyword.trim();

        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        search,
                        search
                );
    }

    public List<Book> getBooksByCategory(String category) {

        if (category == null || category.trim().isEmpty()) {
            return getAllBooks();
        }

        return bookRepository.findByCategoryIgnoreCase(category.trim());
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found"));
    }

    public Book addBook(Book book) {

        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException(
                    "A book with this ISBN already exists"
            );
        }

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {

        Book existingBook = getBookById(id);

        String isbn = updatedBook.getIsbn().trim();

        if (bookRepository.existsByIsbnAndIdNot(isbn, id)) {
            throw new IllegalArgumentException(
                    "Another book already has this ISBN"
            );
        }

        existingBook.setTitle(updatedBook.getTitle().trim());
        existingBook.setAuthor(updatedBook.getAuthor().trim());
        existingBook.setIsbn(isbn);
        existingBook.setCategory(updatedBook.getCategory().trim());
        existingBook.setQuantity(updatedBook.getQuantity());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }

        bookRepository.deleteById(id);
    }

    public void decreaseQuantity(Long id) {

        Book book = getBookById(id);

        if (book.getQuantity() <= 0) {
            throw new IllegalStateException(
                    "Book is currently unavailable"
            );
        }

        book.setQuantity(book.getQuantity() - 1);

        bookRepository.save(book);
    }

    public void increaseQuantity(Long id) {

        Book book = getBookById(id);

        book.setQuantity(book.getQuantity() + 1);

        bookRepository.save(book);
    }
}