package Customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public List<Customer> getAllCusomters(){
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
    }
    
    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody Customer newCustomer){
       // Add a new customer to H2 database
        return customerRepository.save(newCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerRepository.deleteById(id);
    }

    @PutMapping("/customer/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomer){
        return customerRepository.findById(id).map(customer->{
            customer.setEmail(newCustomer.getEmail());
            customer.setName(newCustomer.getName());
            return customerRepository.save(customer);
        }).orElseThrow(()-> new CustomerNotFoundException(id));
    }
}
