package org.democat.status.main.service.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dog {
    private Long id;
    private String name;
    private int age;
    private boolean isActive;
    private String fact;
    private String breed;
    private String favFood;
    private int steps;
}
