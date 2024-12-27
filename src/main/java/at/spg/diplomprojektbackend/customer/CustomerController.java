package at.spg.diplomprojektbackend.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/all")
    public List<Customer> returnAllCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping(path = "/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "/delete/{customerId}")
    public void deleteCustomerById(@PathVariable Long customerId) {
        customerService.deleteCustomerById(customerId);
    }



}
