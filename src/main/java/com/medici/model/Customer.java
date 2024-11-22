package com.medici.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer customerId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String suffix;

    private String email;

    private String mobile;

    private LocalDateTime processDate;

}
