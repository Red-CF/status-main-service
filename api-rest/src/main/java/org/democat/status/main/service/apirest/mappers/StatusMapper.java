package org.democat.status.main.service.apirest.mappers;

import org.democat.status.main.service.apirest.models.DogModel;
import org.democat.status.main.service.domain.dtos.Dog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    Dog convert(DogModel model);

    DogModel convert(Dog dog);

    List<DogModel> convert(List<Dog> dto);
}
