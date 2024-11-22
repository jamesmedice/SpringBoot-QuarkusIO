package com.medici.utils;

import com.medici.model.Customer;
import com.medici.operation.CustomerOperation;

import java.time.LocalDateTime;

public class FunctionalInterfaceOperation {

   public static void minusHours(Customer customer) {
        CustomerOperation customerOperation = customerInterface -> {
            customerInterface.setProcessDate(LocalDateTime.now().minusHours(5L));
        };
        customerOperation.execute(customer);
    }

   public static void plusHours(Customer customer) {
        CustomerOperation customerOperation = customerInterface -> {
            customerInterface.setProcessDate(LocalDateTime.now().plusHours(5L));
        };
        customerOperation.execute(customer);
    }
}
