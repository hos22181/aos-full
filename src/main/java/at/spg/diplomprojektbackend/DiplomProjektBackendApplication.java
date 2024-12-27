package at.spg.diplomprojektbackend;

import at.spg.diplomprojektbackend.customer.Customer;
import at.spg.diplomprojektbackend.customer.CustomerRepository;
import at.spg.diplomprojektbackend.role.Role;
import at.spg.diplomprojektbackend.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@AllArgsConstructor
public class DiplomProjektBackendApplication implements CommandLineRunner {

    private RoleRepository roleRepository;

    private CustomerRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DiplomProjektBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if not present
        if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
            roleRepository.save(new Role(null, "ROLE_ADMIN"));
        }
        if (!roleRepository.findByName("ROLE_USER").isPresent()) {
            roleRepository.save(new Role(null, "ROLE_USER"));
        }

        // Check if an admin user exists, if not, create one
        if (!userRepository.findByEmail("admin@example.com").isPresent()) {
            Customer adminUser = new Customer();
            adminUser.setEmail("admin@example.com");
            adminUser.setVerified(true);
            adminUser.setPassword(passwordEncoder.encode("1234"));  // Set password to "1234" and encode it
            adminUser.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN").get()));  // Assign "ROLE_ADMIN"
            userRepository.save(adminUser);
        }
    }
}
