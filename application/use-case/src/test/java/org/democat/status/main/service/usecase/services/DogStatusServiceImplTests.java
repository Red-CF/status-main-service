package org.democat.status.main.service.usecase.services;

import lombok.SneakyThrows;
import org.democat.status.main.service.domain.dtos.Dog;
import org.democat.status.main.service.usecase.ports.out.DogStatusRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogStatusServiceImplTests {

    @Mock
    private DogStatusRepository repository;
    @InjectMocks
    private DogStatusServiceImpl service;

    private static final List<Dog> DOG_LIST = List.of(
            new Dog(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123),
            new Dog(2L, "terry",4, false,"Despises children", "Caucasian Shepherd Dog", "carrots", 20),
            new Dog(3L, "toby", 8, true,"Was an old police dog", "Bouvier des Flandres", "wagyu", 32)
    );

    private final Optional<Dog> OPTIONAL_OBJECT = Optional.of(new Dog(1L, "sam", 2, true, "Has blue eyes", "golden retriever", "corn", 123));

    private final Dog SINGLE_OBJECT = new Dog(1L, "sam", 2,  false,"Has blue eyes", "golden retriever", "corn", 123);

    @Test
    @SneakyThrows
    @DisplayName("Should get all dog statuses")
    void shouldGetAllDogStatuses() {
        when(repository.findAll()).thenReturn(DOG_LIST);

        var result = service.list();

        assertThat("The lists of dogs should be equal", result, equalTo(DOG_LIST));
    }


    @Test
    @SneakyThrows
    @DisplayName("Should get a dog status by name")
    void shouldGetDogStatusByName() {
        when(repository.getByName("sam")).thenReturn(OPTIONAL_OBJECT);

        var result = service.getByName("sam");

        assertThat("The lists of dogs should be equal", result, equalTo(OPTIONAL_OBJECT));
    }


    @Test
    @SneakyThrows
    @DisplayName("Should add a dog status")
    void shouldAddADogStatus() {
        service.create(SINGLE_OBJECT);

        verify(repository, times(1)).save(SINGLE_OBJECT);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw a error when the given dog is null")
    void shouldThrowExceptionWhenAddADogIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.create(null),"Should throw illegal argument exception");
    }

    @Test
    @SneakyThrows
    @DisplayName("Should update a dog status")
    void shouldUpdateADogStatus() {
        service.update(SINGLE_OBJECT);

        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(SINGLE_OBJECT);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should delete a dog status")
    void shouldDeleteADogStatus() {
        service.delete(SINGLE_OBJECT.getId());

        verify(repository, times(1)).deleteById(1L);
    }

}
