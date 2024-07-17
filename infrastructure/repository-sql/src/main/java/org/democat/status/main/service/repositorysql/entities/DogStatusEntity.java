package org.democat.status.main.service.repositorysql.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@Table(name = "o_status")
@NamedQuery(name = "DogStatusEntity.getByName", query = "SELECT p FROM DogStatusEntity p WHERE p.name = :name")
@NamedQuery(name = "DogStatusEntity.getOneByBreed", query = "SELECT p FROM DogStatusEntity p WHERE p.breed = :breed")
public class DogStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "isactive")
    private Boolean isActive;
    @Column(name = "fact")
    private String fact;
    @Column(name = "breed")
    private String breed;
    @Column(name = "food")
    private String food;
    @Column(name = "steps")
    private Integer steps;

}
