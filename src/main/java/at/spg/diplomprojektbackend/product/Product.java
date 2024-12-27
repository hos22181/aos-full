package at.spg.diplomprojektbackend.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

@Entity
@Table
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private Category category;
    private Unit unit;
    private Double price;
    private Integer weight;
    private String pic_url_front;
    private String pic_url_back;
    private String description;

    @Builder
    public Product(String pic_url_front, String pic_url_back, String name, Double price,Integer weight, String description, Category category, Unit unit) {
        this.pic_url_front = pic_url_front;
        this.pic_url_back = pic_url_back;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.description = description;
        this.category = category;
        this.unit = unit;
    }

    public Product() {
    }
}
