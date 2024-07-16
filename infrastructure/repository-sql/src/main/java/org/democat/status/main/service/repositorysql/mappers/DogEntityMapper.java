package org.democat.status.main.service.repositorysql.mappers;

import org.democat.status.main.service.domain.dtos.Dog;
import org.democat.status.main.service.repositorysql.entities.DogStatusEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DogEntityMapper {

    List<Dog> convert(List<DogStatusEntity> entities);

    Dog convert(DogStatusEntity entities);

    DogStatusEntity convert(Dog entities);
}
