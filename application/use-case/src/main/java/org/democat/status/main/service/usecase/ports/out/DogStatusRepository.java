package org.democat.status.main.service.usecase.ports.out;

import org.democat.status.main.service.domain.dtos.Dog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DogStatusRepository extends CrudRepository<Dog, Long> {

    @Override
    List<Dog> findAll();

    Optional<Dog> getByName(String name);

    Optional<Dog> getOneByBreed(String breed);
}
