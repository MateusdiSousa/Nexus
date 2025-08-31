package nexus.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexus.inventory.model.Book;
import nexus.inventory.service.BookService;
import nexus.inventory.dto.BookDto;
import nexus.inventory.exceptions.BadRequestException;
import nexus.inventory.exceptions.ConflictException;
import nexus.inventory.exceptions.NotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/book")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody BookDto dto) throws ConflictException {
        Book createdBook = service.createBook(dto);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getBooksBySearch(@RequestParam("search") String word) throws BadRequestException {
        List<Book> books = service.findBooksByWord(word);
        return ResponseEntity.ok(books);
    }

    @GetMapping("category")
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam("category") List<Integer> codes) throws BadRequestException, NotFoundException {
        List<Book> books = service.findBooksByCategory(codes);
        return ResponseEntity.ok(books);
    }
}
