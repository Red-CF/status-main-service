package org.democat.status.main.service.usecase.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.democat.status.main.service.domain.dtos.Dog;
import org.democat.status.main.service.usecase.ports.in.DogStatusService;
import org.democat.status.main.service.usecase.ports.out.DogStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class DogStatusServiceImpl implements DogStatusService {
    private DogStatusRepository repository;

    @Override
    public List<Dog> list() {
        return repository.findAll();
    }

    @Override
    public Optional<Dog> getByName(String name) {
        Optional<Dog> requestedData = repository.getByName(name);
        if (requestedData.isEmpty()) {
            log.error("No Dog found with id {}", name);
            return requestedData;
        }
        return requestedData;
    }

    @Override
    public Optional<Integer> getStepCount(Long id) {
        var requestedData = repository.findById(id);
        if (requestedData.isEmpty()) {
            throw new IllegalArgumentException("No Dog found with id " + id);
        }

        return Optional.of(requestedData.get().getSteps());
    }

    @Override
    public Optional<Dog> getOneByBreedRandomly(String breed) {
        Optional<Dog> requestedData = repository.getOneByBreed(breed);
        if (requestedData.isEmpty()) {
            log.error("There`s no dog by the specified breed {}", breed);
            return requestedData;
        }
        return requestedData;
    }

    @Override
    public void create(Dog dog) {
        if (Objects.isNull(dog)){
            log.error("The entered dog has null elements");
            throw new IllegalArgumentException("The entered dog has null elements");
        }
        repository.save(dog);
    }

    @Override
    public void update(Dog dog) {
        var isRegistered = repository.existsById(dog.getId());
        if (!isRegistered){
            log.warn("The dog with id {} does not exist", dog.getId());
        }
        create(dog);
    }

    @Override
    public void deactivate(Long id) {
        var registeredEntity = repository.findById(id).orElse(null);
        if (null == registeredEntity) {
            log.error("The dog with the given id doesn't exist");
            throw new IllegalArgumentException("The dog with the given id doesn't exist");
        }
        registeredEntity.setActive(false);
        repository.save(registeredEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
