package org.democat.status.main.service.apirest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DogModel {
    private Long id;
    private String name;
    private int age;
    private boolean isActive;
    private String fact;
    private String breed;
    private String favFood;
    private int steps;
}
