package at.spg.diplomprojektbackend.jwt;

import at.spg.diplomprojektbackend.security.LoginRequest;
import at.spg.diplomprojektbackend.customer.Customer;
import at.spg.diplomprojektbackend.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
@AllArgsConstructor
public class AuthService {

    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse authenticateAndLogin(LoginRequest loginRequest) {
        // Fetch customer from database
        Customer customer = customerService.getCustomer(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Verify password
        if (!passwordEncoder.matches(loginRequest.getPassword(), customer.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customer.getEmail(), loginRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Authentication failed");
        }

        // Generate JWT token
        String jwtToken = jwtService.generateToken(customer.getEmail(), customer.getRoles().iterator().next());

        // Check roles and set redirect paths
        boolean isAdmin = customer.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));

        String role = isAdmin ? "ADMIN" : "USER";
        String redirect = isAdmin ? "/admin-page" : "/products";

        // Return response DTO
        return new LoginResponse("Login successful", role, redirect, customer.isVerified(), jwtToken);
    }


}
