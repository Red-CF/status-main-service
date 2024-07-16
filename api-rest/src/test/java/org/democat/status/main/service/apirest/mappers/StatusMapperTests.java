package org.democat.status.main.service.apirest.mappers;

import lombok.SneakyThrows;
import org.democat.status.main.service.apirest.models.DogModel;
import org.democat.status.main.service.domain.dtos.Dog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class StatusMapperTests {

    private StatusMapper statusMapper = new StatusMapperImpl();

    private static final Dog DOMAIN_OBJECT = new Dog(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123);
    private static final DogModel MODEL_OBJECT = new DogModel(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123);

    private static final List<Dog> DOMAIN_LIST = List.of(
            new Dog(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123),
            new Dog(2L, "terry",4, true,"Despises children", "Caucasian Shepherd Dog", "carrots", 20),
            new Dog(3L, "toby", 8, true,"Was an old police dog", "Bouvier des Flandres", "wagyu", 32)
    );

    private static final List<DogModel> MODEL_LIST = List.of(
            new DogModel(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123),
            new DogModel(2L, "terry",4, true,"Despises children", "Caucasian Shepherd Dog", "carrots", 20),
            new DogModel(3L, "toby", 8, true,"Was an old police dog", "Bouvier des Flandres", "wagyu", 32)
    );

    @Test
    @SneakyThrows
    @DisplayName("Should map a domain object")
    void shouldMapDomainObject() {
        var result = statusMapper.convert(MODEL_OBJECT);

        assertEquals(DOMAIN_OBJECT, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should map a model object")
    void shouldMapModelObject() {
        var result = statusMapper.convert(DOMAIN_OBJECT);

        assertEquals(MODEL_OBJECT, result);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should map a model list")
    void shouldMapModelList() {
        var result = statusMapper.convert(DOMAIN_LIST);

        assertEquals(MODEL_LIST, result);
    }
}
