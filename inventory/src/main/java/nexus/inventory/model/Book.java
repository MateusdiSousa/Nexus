package nexus.inventory.model;

import java.math.BigInteger;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nexus.inventory.dto.BookDto;

@Entity(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    public Book(BookDto bookDto) {
        this.autor = bookDto.autor();
        this.bookCode = bookDto.bookCode();
        this.price = bookDto.price();
        this.publisher = bookDto.publisher();
        this.publishYear = bookDto.publishYear();
        this.stock = bookDto.stock();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false, length = 12)
    private Long bookCode;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false, length = 80)
    private String autor;

    @Column(nullable = false, length = 80)
    private String publisher;

    @Column(nullable = false, length = 80)
    private Integer publishYear;

    @ManyToMany
    @JoinTable(
        name = "book_category", 
        joinColumns = @JoinColumn(name = "book_id"), 
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> bookCategory;

    @Column(nullable = false)
    private BigInteger price;

    @Column(nullable = false)
    private Long stock;
}
