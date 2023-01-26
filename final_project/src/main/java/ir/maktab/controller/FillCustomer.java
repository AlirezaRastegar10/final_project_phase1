package ir.maktab.controller;

import ir.maktab.entity.Customer;

public class FillCustomer {

    public Customer getCustomer() {
        return new Customer(1_000_000L);
    }
}
