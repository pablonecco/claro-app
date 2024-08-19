package com.example.claro_application.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreateCustomerDTO {

    @Size(min = 2, max = 20, message = "El nombre debe tener entre 2 y 20 caracteres")
    private String name;

    @Size(min = 2, max = 20, message = "El apellido debe tener entre 2 y 20 caracteres")
    private String lastName;

    @NotNull
    private int document;
}
