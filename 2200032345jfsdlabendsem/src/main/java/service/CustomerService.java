package service;

import model.Customer;
import repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer updateCustomer(Long id, String name, String address) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setName(name);
            customer.setAddress(address);
            return customerRepository.save(customer);
        }
        return null;
    }
}
