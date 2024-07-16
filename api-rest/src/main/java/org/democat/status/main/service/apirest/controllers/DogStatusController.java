package org.democat.status.main.service.apirest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.democat.status.main.service.apirest.mappers.StatusMapper;
import org.democat.status.main.service.apirest.models.DogModel;
import org.democat.status.main.service.domain.dtos.Dog;
import org.democat.status.main.service.usecase.ports.in.DogStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/dogs")
public class DogStatusController {

   private DogStatusService statusService;
   private StatusMapper mapper;

   @GetMapping("/index")
   public ResponseEntity<List<DogModel>> index(){
       List<Dog> domainList = statusService.list();
       List<DogModel> modelList = mapper.convert(domainList);

       return modelList.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(modelList);
   }

   @GetMapping("/by-name/{name}")
   public ResponseEntity<DogModel> findByName(@PathVariable String name){
      Optional<Dog> status = statusService.getByName(name);
      if (status.isEmpty()) {
          return ResponseEntity.notFound().build();
      }
      DogModel model = mapper.convert(status.get());

      return ResponseEntity.ok(model);
   }

    @GetMapping("/step-count/{id}")
    public ResponseEntity<Integer> getStepCount(@PathVariable Long id){
        Optional<Integer> status = statusService.getStepCount(id);
        return status.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/random-by-breed/{breed}")
    public ResponseEntity<DogModel> randomByBreed(@PathVariable String breed){
       Optional<Dog> status = statusService.getOneByBreedRandomly(breed);
       if (status.isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       DogModel model = mapper.convert(status.get());
       return ResponseEntity.ok(model);
    }


    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody DogModel model){
       var dto = mapper.convert(model);
       try {
           statusService.create(dto);
       }catch (IllegalArgumentException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
       return ResponseEntity.ok("Dog status added");
    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody DogModel model){
        var dto = mapper.convert(model);
        try {
            statusService.update(dto);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Dog status updated");
    }

    @PatchMapping("deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable Long id){
        try {
            statusService.deactivate(id);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Dog status deactivated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
       try {
           statusService.delete(id);
       } catch (IllegalArgumentException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
       return ResponseEntity.ok("Dog status deleted");
    }
}
