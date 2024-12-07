package controller;

import model.Customer;
import repository.CustomerRepository;
import service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    // Show form to create a customer
    @GetMapping("/create")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }

    // Handle form submission for creating a customer
    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers/list";
    }

    // Show form to update a customer
    @GetMapping("/update")
    public String showUpdateCustomerForm(@RequestParam Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "update-customer";
        } else {
            return "error"; // Redirect to an error page if the customer doesn't exist
        }
    }

    // Handle form submission for updating a customer
    @PostMapping("/update")
    public String updateCustomer(@RequestParam Long id, @RequestParam String name, @RequestParam String address) {
        customerService.updateCustomer(id, name, address);
        return "redirect:/customers/list";
    }

    // List all customers
    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    // Error page
    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
}
