package org.democat.status.main.service.apirest.controllers;

import lombok.SneakyThrows;
import org.democat.status.main.service.apirest.mappers.StatusMapper;
import org.democat.status.main.service.apirest.models.DogModel;
import org.democat.status.main.service.domain.dtos.Dog;
import org.democat.status.main.service.usecase.ports.in.DogStatusService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogStatusControllerTests {
    @Mock
    private DogStatusService service;
    @Mock
    private StatusMapper mapper;
    @InjectMocks
    private DogStatusController controller;

    private static final Dog DOMAIN_OBJECT = new Dog(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123);
    private static final DogModel MODEL = new DogModel(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123);

    private static final List<Dog> DOG_LIST = List.of(
            new Dog(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123),
            new Dog(2L, "terry",4, false,"Despises children", "Caucasian Shepherd Dog", "carrots", 20),
            new Dog(3L, "toby", 8, true,"Was an old police dog", "Bouvier des Flandres", "wagyu", 32)
    );

    private static final List<DogModel> DOG_MODEL_LIST = List.of(
            new DogModel(1L, "sam", 2, true,"Has blue eyes", "golden retriever", "corn", 123),
            new DogModel(2L, "terry",4, false,"Despises children", "Caucasian Shepherd Dog", "carrots", 20),
            new DogModel(3L, "toby", 8, true,"Was an old police dog", "Bouvier des Flandres", "wagyu", 32)
    );


    @Test
    @SneakyThrows
    @DisplayName("When the list is correct it should respond with an status 2xx OK")
    void shouldGetAModelList(){
        when(service.list()).thenReturn(DOG_LIST);
        when(mapper.convert(DOG_LIST)).thenReturn(DOG_MODEL_LIST);

        var result = controller.index();

        verify(service, times(1)).list();
        verify(mapper, times(1)).convert(DOG_LIST);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.toString(), equalTo("""
                <200 OK OK,[DogModel(id=1, name=sam, age=2, isActive=true, fact=Has blue eyes, breed=golden retriever, favFood=corn, steps=123), DogModel(id=2, name=terry, age=4, isActive=false, fact=Despises children, breed=Caucasian Shepherd Dog, favFood=carrots, steps=20), DogModel(id=3, name=toby, age=8, isActive=true, fact=Was an old police dog, breed=Bouvier des Flandres, favFood=wagyu, steps=32)],[]>"""));
    }

    @Test
    @SneakyThrows
    @DisplayName("When the list is null, it should respond with an status 4xx KO")
    void shouldBadRequestWhenGetAModelListIsNull(){
        when(service.list()).thenReturn(DOG_LIST);

        var result = controller.index();

        assertThat("Response should be 404", result.getStatusCode().is4xxClientError(), equalTo(true));
    }

    @Test
    @SneakyThrows
    @DisplayName("When an entity exists with the given name is OK it should respond with an status 2xx OK")
    void shouldGetAStatusByName(){
        when(service.getByName("sam")).thenReturn(Optional.of(DOMAIN_OBJECT));
        when(mapper.convert(DOMAIN_OBJECT)).thenReturn(MODEL);

        var result = controller.findByName("sam");

        verify(service, times(1)).getByName("sam");
        verify(mapper, times(1)).convert(DOMAIN_OBJECT);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.toString(), equalTo("<200 OK OK,DogModel(id=1, name=sam, age=2, isActive=true, fact=Has blue eyes, breed=golden retriever, favFood=corn, steps=123),[]>"));
    }

    @Test
    @SneakyThrows
    @DisplayName("When there's no entity by the name it should respond with an status 4xx KO")
    void shouldGetAStatusKOWhenEntityNullByName(){
        var result = controller.findByName("sam");

        verify(service, times(1)).getByName("sam");
        assertThat("Response should be Successful", result.getStatusCode().is4xxClientError(), equalTo(true));
    }


    @Test
    @SneakyThrows
    @DisplayName("When receiving an existing ID it should respond with an status 2xx OK")
    void shouldGetAStatusNumberOfSteps(){
        when(service.getStepCount(1L)).thenReturn(Optional.of(DOMAIN_OBJECT.getSteps()));

        var result = controller.getStepCount(1L);

        verify(service, times(1)).getStepCount(1L);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.toString(), equalTo("<200 OK OK,123,[]>"));
    }

    @Test
    @SneakyThrows
    @DisplayName("When receiving a non existing ID it should respond with an status 4xx KO")
    void shouldGetAErrorStatusWhenNullIdGiven(){

        var result = controller.getStepCount(1L);

        verify(service, times(1)).getStepCount(1L);
        assertThat("Response should be Successful", result.getStatusCode().is4xxClientError(), equalTo(true));
    }

    @Test
    @SneakyThrows
    @DisplayName("When receiving a current breed it should respond with an status 2xx OK")
    void shouldGetAStatusRandomBreed(){
        when(service.getOneByBreedRandomly("golden retriever")).thenReturn(Optional.of(DOMAIN_OBJECT));
        when(mapper.convert(DOMAIN_OBJECT)).thenReturn(MODEL);

        var result = controller.randomByBreed("golden retriever");

        verify(service, times(1)).getOneByBreedRandomly("golden retriever");
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.toString(), equalTo("<200 OK OK,DogModel(id=1, name=sam, age=2, isActive=true, fact=Has blue eyes, breed=golden retriever, favFood=corn, steps=123),[]>"));
    }

    @Test
    @SneakyThrows
    @DisplayName("When adding a correct object it should respond with an status 2xx OK")
    void shouldAddAStatus(){
        when(mapper.convert(MODEL)).thenReturn(DOMAIN_OBJECT);

        var result = controller.add(MODEL);

        verify(service, times(1)).create(DOMAIN_OBJECT);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.getBody(), equalTo("Dog status added"));
    }

    @Test
    @SneakyThrows
    @DisplayName("When updating a correct object it should respond with an status 2xx OK")
    void shouldUpdateAStatus(){
        when(mapper.convert(MODEL)).thenReturn(DOMAIN_OBJECT);

        var result = controller.update(MODEL);

        verify(service, times(1)).update(DOMAIN_OBJECT);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.getBody(), equalTo("Dog status updated"));
    }

    @Test
    @SneakyThrows
    @DisplayName("When deactivating a status with correct ID it should respond with an status 2xx OK")
    void shouldDeactivateAStatus(){
        var result = controller.deactivate(1L);

        verify(service, times(1)).deactivate(1L);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.getBody(), equalTo("Dog status deactivated"));
    }

    @Test
    @SneakyThrows
    @DisplayName("When deleting a status with correct ID it should respond with an status 2xx OK")
    void shouldDeleteAStatus(){
        var result = controller.delete(1L);

        verify(service, times(1)).delete(1L);
        assertThat("Response should be Successful", result.getStatusCode().is2xxSuccessful(), equalTo(true));
        assertThat("Response should be Successful", result.getBody(), equalTo("Dog status deleted"));
    }
}
