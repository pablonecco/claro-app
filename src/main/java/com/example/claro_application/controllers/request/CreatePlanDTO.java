package com.example.claro_application.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CreatePlanDTO {
    private Integer sms;
    private Integer minutes;
    private Integer mb;
    private Integer price;
    private String name;
}
