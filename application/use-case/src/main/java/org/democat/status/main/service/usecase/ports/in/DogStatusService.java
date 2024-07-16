package org.democat.status.main.service.usecase.ports.in;

import org.democat.status.main.service.domain.dtos.Dog;

import java.util.List;
import java.util.Optional;

public interface DogStatusService {
    List<Dog> list();

    Optional<Dog> getByName(String name);

    Optional<Integer> getStepCount(Long id);

    Optional<Dog> getOneByBreedRandomly(String breed);

    void create(Dog dog);

    void update(Dog dog);

    void deactivate(Long id);

    void delete(Long id);
}
