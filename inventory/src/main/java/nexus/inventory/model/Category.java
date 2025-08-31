package nexus.inventory.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nexus.inventory.dto.CategoryDto;

@Entity(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 4)
    private Integer categoryCode;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "bookCategory")
    @JsonIgnore
    Set<Book> books;

    public Category(CategoryDto dto) {
        this.name = dto.name();
        this.categoryCode = dto.code();
    } 
}
