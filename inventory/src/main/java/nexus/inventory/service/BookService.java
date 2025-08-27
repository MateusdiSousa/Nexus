package nexus.inventory.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nexus.inventory.dto.BookDto;
import nexus.inventory.exceptions.BadRequestException;
import nexus.inventory.exceptions.ConflictException;
import nexus.inventory.exceptions.NotFoundException;
import nexus.inventory.model.Book;
import nexus.inventory.model.Category;
import nexus.inventory.repository.BookRepository;
import nexus.inventory.repository.CategoryRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Book> findBooksByWord(String term) throws BadRequestException {
        if (term == null || term.isBlank() ) {
            throw new BadRequestException("Word search cannot be empty");
        }
        return bookRepository.searchBooks(term.toLowerCase());
    }

    public Book createBook(BookDto bookDto) throws ConflictException {
        bookRepository.findByBookCode(bookDto.bookCode())
                .orElseThrow(() -> new ConflictException("Already exists a book with this code"));

        Book newBook = new Book(bookDto);

        if (bookDto.listCodeCategory().size() > 0) {
            Set<Category> categories = categoryRepository.findByCategoryCodeIn(bookDto.listCodeCategory()).stream()
                    .collect(Collectors.toSet());
            newBook.setBookCategory(categories);
        }

        return bookRepository.save(newBook);
    }

    public List<Book> findBooksByCategory(List<String> codes) throws NotFoundException, BadRequestException {
        if (codes.isEmpty()) {
            throw new BadRequestException("Category code list is mandatory.");
        }

        Set<Category> categories = categoryRepository.findByCategoryCodeIn(codes).stream().collect(Collectors.toSet());
        if (categories.isEmpty()) {
            throw new NotFoundException("Categories not found.");
        }

        return bookRepository.findByBookCategory(categories);
    }
}
