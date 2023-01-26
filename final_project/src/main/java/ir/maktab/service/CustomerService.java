package ir.maktab.service;

import ir.maktab.entity.Customer;
import ir.maktab.repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository = new CustomerRepository();

    public void create(Customer customer) {
        customerRepository.create(customer);
    }

    public Customer findById(Long id){
        return customerRepository.findById(id);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public void delete(Long id){
        customerRepository.delete(id);
    }

    public Customer findByUserId(Long id) {
        return customerRepository.getCustomerByUserId(id);
    }
}
