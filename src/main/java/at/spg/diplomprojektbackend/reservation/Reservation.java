package at.spg.diplomprojektbackend.reservation;

import at.spg.diplomprojektbackend.customer.Customer;
import at.spg.diplomprojektbackend.product.Product;
import at.spg.diplomprojektbackend.productperreservation.ProductPerReservation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private Customer customer;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductPerReservation> productPerReservations;

    @Builder
    public Reservation(Customer customer, List<ProductPerReservation> productPerReservations) {
        this.customer = customer;
        this.productPerReservations = productPerReservations;
    }
}