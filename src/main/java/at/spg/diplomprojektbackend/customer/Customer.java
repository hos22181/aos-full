package at.spg.diplomprojektbackend.customer;

import at.spg.diplomprojektbackend.role.Role;
import at.spg.diplomprojektbackend.verificationtoken.VerificationToken;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private boolean verified = false;


    @Builder
    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Eager fetching to load roles with user
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(
                    name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Getter
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private VerificationToken verificationToken;


    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
        verificationToken.setCustomer(this);
    }
}





