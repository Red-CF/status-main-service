package org.democat.status.main.service.repositorysql.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.democat.status.main.service.domain.dtos.Dog;
import org.democat.status.main.service.repositorysql.StatusRepository;
import org.democat.status.main.service.repositorysql.entities.DogStatusEntity;
import org.democat.status.main.service.repositorysql.mappers.DogEntityMapper;
import org.democat.status.main.service.usecase.ports.out.DogStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public abstract class DogRepositoryService implements DogStatusRepository {

    private StatusRepository statusRepository;
    private DogEntityMapper dogEntityMapper;

    @Override
    public List<Dog> findAll() {
        List<DogStatusEntity> entityList = statusRepository.findAll();
        return dogEntityMapper.convert(entityList);
    }

    @Override
    public Optional<Dog> getByName(String name) {
        Optional<DogStatusEntity> entity = statusRepository.getByName(name);
        return entity.map(dogStatusEntity -> dogEntityMapper.convert(dogStatusEntity));
    }

    @Override
    public Optional<Dog> getOneByBreedRandomly(String breed) {
        Optional<DogStatusEntity> entity = statusRepository.getOneByBreedRandomly(breed);
        return entity.map(dogStatusEntity -> dogEntityMapper.convert(dogStatusEntity));
    }
}
