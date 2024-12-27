package at.spg.diplomprojektbackend.customer;

import at.spg.diplomprojektbackend.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    private EmailService emailService;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void addNewCustomer(Customer customer) {
        if(customerRepository.findByEmail(customer.getEmail()).isPresent()){
            throw new IllegalArgumentException("Customer-Email"+customer.getEmail()+" already exists");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomerById(Long customerId){
        boolean exists=customerRepository.existsById(customerId);
        if(!exists){
            throw new IllegalStateException("Customer with id "+customerId+" does not exist");
        }
        customerRepository.deleteById(customerId);
    }

    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
        emailService.sendVerificationEmail(customer.getEmail(), customer.getVerificationToken().getToken()); // Pass user ID directly
    }

    public Optional<Customer> getCustomer(String email) {
       return customerRepository.findByEmail(email);
    }

}
