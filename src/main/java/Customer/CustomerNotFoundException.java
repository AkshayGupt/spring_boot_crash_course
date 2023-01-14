package Customer;

public class CustomerNotFoundException extends RuntimeException{
    CustomerNotFoundException(Long id){
        super(String.format("Customer with id %s not found", id));
    }
}
