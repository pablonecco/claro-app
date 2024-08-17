package com.example.claro_application.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateCustomerDTO {
    private String name;
    private String lastName;
    private Integer document;
}
