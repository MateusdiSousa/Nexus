package nexus.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexus.inventory.model.Book;
import nexus.inventory.service.BookService;
import nexus.inventory.dto.BookDto;
import nexus.inventory.dto.StockResponse;
import nexus.inventory.exceptions.BadRequestException;
import nexus.inventory.exceptions.ConflictException;
import nexus.inventory.exceptions.NotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/books")
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
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam("categories") List<Integer> codes)
            throws BadRequestException, NotFoundException {
        List<Book> books = service.findBooksByCategory(codes);
        return ResponseEntity.ok(books);
    }

    @PostMapping()
    public ResponseEntity<Book> updateBook(@RequestBody BookDto dto) throws ConflictException {
        Book updatedBook = service.updateBook(dto);
        return ResponseEntity.ok(updatedBook);
    }

    @PutMapping("{book_code}/increase-stock")
    public ResponseEntity<StockResponse> increaseStock(@PathVariable Long bookCode,
            @RequestParam("quantity") Long quantity) throws NotFoundException {
        Long newStock = service.increaseStock(bookCode, quantity);

        StockResponse response = new StockResponse(quantity, bookCode, newStock);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{book_code}/decrease-stock")
    public ResponseEntity<StockResponse> decreaseStock(@PathVariable Long bookCode,
            @RequestParam("quantity") Long quantity) throws NotFoundException {
        Long newStock = service.decreaseStock(bookCode, quantity);

        StockResponse response = new StockResponse(quantity, bookCode, newStock);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{book_code}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookCode) {
        service.deleteBook(bookCode);

        return ResponseEntity.ok("Book deleted sucessfully");
    }
}
