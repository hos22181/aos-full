package at.spg.diplomprojektbackend.productperreservation;

import at.spg.diplomprojektbackend.product.Product;
import at.spg.diplomprojektbackend.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ProductPerReservation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonManagedReference
    private Reservation reservation;

    private Integer quantity; // Anzahl der Produkte in der Reservation

    @Builder
    public ProductPerReservation(Product product, Reservation reservation, Integer quantity) {
        this.product = product;
        this.reservation = reservation;
        this.quantity = quantity;
    }
}
