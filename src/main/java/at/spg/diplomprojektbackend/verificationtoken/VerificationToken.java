package at.spg.diplomprojektbackend.verificationtoken;


import at.spg.diplomprojektbackend.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private LocalDateTime expiryDate;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public VerificationToken(String token, Customer customer, LocalDateTime expiryDate) {
        this.token = token;
        this.customer = customer;
        this.expiryDate = expiryDate;
    }
}
