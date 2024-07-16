package org.democat.status.main.service.repositorysql;

import org.democat.status.main.service.repositorysql.entities.DogStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<DogStatusEntity, Long> {
    Optional<DogStatusEntity> getByName(String name);

    Optional<DogStatusEntity> getOneByBreedRandomly(String breed);
}
