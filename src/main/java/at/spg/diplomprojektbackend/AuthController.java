package at.spg.diplomprojektbackend;

import at.spg.diplomprojektbackend.customer.Customer;
import at.spg.diplomprojektbackend.customer.CustomerRepository;
import at.spg.diplomprojektbackend.jwt.JwtService;
import at.spg.diplomprojektbackend.role.Role;
import at.spg.diplomprojektbackend.role.RoleRepository;
import at.spg.diplomprojektbackend.jwt.AuthService;
import at.spg.diplomprojektbackend.jwt.LoginResponse;
import at.spg.diplomprojektbackend.security.LoginRequest;
import at.spg.diplomprojektbackend.security.RegisterRequest;
import at.spg.diplomprojektbackend.verificationtoken.VerificationToken;
import at.spg.diplomprojektbackend.verificationtoken.VerificationTokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder;

    private VerificationTokenRepository verificationTokenRepository;

    private EmailService emailService;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private AuthService authService;

    private final JwtService jwtService; // Inject JwtService


    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        // Check if email already exists
        if (customerRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        // Create a new customer
        Customer customer = new Customer();
        customer.setEmail(registerRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Assign default role to the customer (optional, you can add more roles here)
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("User role not found"));
        customer.getRoles().add(userRole);

        // Save the customer first to get a valid ID for the token association
        customerRepository.save(customer);

        // Generate token and associate it with the customer
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, customer, LocalDateTime.now().plusHours(24));
        verificationTokenRepository.save(verificationToken);

        // Send the verification email
        emailService.sendVerificationEmail(customer.getEmail(), token);

        // Return a success message
        return ResponseEntity.ok("Registration successful. Please verify your email.");
    }

    // GET /api/auth/verify (email verification)
    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam("token") String token) {
        // Find the verification token
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElse(null);

        // Check if the token is valid and not expired
        if (verificationToken == null || verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }

        // Set the user as verified
        Customer customer = verificationToken.getCustomer();
        customer.setVerified(true);
        customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "https://aos-spg-full.vercel.app/")  // Redirect to the frontend
                .build();

    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Delegate the login operation to the service layer
            LoginResponse response = authService.authenticateAndLogin(loginRequest);

            return ResponseEntity.ok(response);

        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Default endpoint after login (for role-based redirects)
    @GetMapping("/default")
    public ResponseEntity<?> defaultAfterLogin(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return ResponseEntity.ok("Admin dashboard access");
            } else {
                return ResponseEntity.ok("Customer dashboard access");
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You need to log in first");
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("username", authentication.getName());
        response.put("roles", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList());
        return ResponseEntity.ok(response);
    }
}
