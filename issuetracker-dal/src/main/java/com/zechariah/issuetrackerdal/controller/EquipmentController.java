package com.zechariah.issuetrackerdal.controller;

import com.zechariah.issuetrackerdal.exceptions.MachineNotFound;
import com.zechariah.issuetrackerdal.model.EquipmentModel;
import com.zechariah.issuetrackerdal.model.assembler.EquipmentAssembler;
import com.zechariah.issuetrackerdal.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EquipmentController {

    @Autowired
    EquipmentRepository repository;

    EquipmentAssembler assembler;

    EquipmentController(EquipmentRepository repository, EquipmentAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping(value = "/equipment")
    public CollectionModel<EntityModel<EquipmentModel>> getAll() {

        List<EntityModel<EquipmentModel>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(EquipmentController.class).getAll()).withSelfRel());
    }

    @PostMapping(value = "/equipment")
    public ResponseEntity<?> newMachine(@RequestBody EquipmentModel newMachine) {

        EntityModel<EquipmentModel> entityModel = assembler.toModel(repository.save(newMachine));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @GetMapping(value = "/equipment/{id}")
    public EntityModel<EquipmentModel> oneMachine(@PathVariable Long id) {

        EquipmentModel machine = repository.findById(id)
                .orElseThrow(() -> new MachineNotFound(id));

        return assembler.toModel(machine);
    }

    @PutMapping("/equipment/{id}")
    public EquipmentModel replaceMachine(@RequestBody EquipmentModel newMachine, @PathVariable Long id) {

        return repository.findById(id)
                .map(tool -> {
                    tool.setName(newMachine.getName());
                    tool.setDescription(newMachine.getDescription());
                    tool.setLocation(newMachine.getLocation());
                    return repository.save(tool);
                })
                .orElseGet(() -> {
                    newMachine.setId(id);
                    return repository.save(newMachine);
                });
    }

    @DeleteMapping(value = "/equipment/{id}")
    public ResponseEntity<?> deleteMachine(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
