package com.medici.operation;

import com.medici.model.Customer;

@FunctionalInterface
public interface CustomerOperation {
    void execute(Customer customer);
}
