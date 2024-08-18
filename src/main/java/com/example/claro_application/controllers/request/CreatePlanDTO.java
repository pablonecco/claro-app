package com.example.claro_application.controllers.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreatePlanDTO {

    @NotNull
    private Integer sms;

    @NotNull
    private Integer minutes;

    @NotNull
    private Integer mb;

    @NotNull
    private Integer price;

    @Size(min = 5, max = 10, message = "El nombre debe tener entre 5 y 10 caracteres")
    private String name;
}
