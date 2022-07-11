package com.zechariah.issuetrackerdal.controller;

import com.zechariah.issuetrackerdal.exceptions.UserNotFoundException;
import com.zechariah.issuetrackerdal.model.UserModel;
import com.zechariah.issuetrackerdal.model.assembler.UserModelAssembler;
import com.zechariah.issuetrackerdal.repository.RoleRepository;
import com.zechariah.issuetrackerdal.repository.UserRepository;
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
public class UserController {
    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;

    UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/users")
    public CollectionModel<EntityModel<UserModel>> getAll() {

        List<EntityModel<UserModel>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @PostMapping(value = "/users")
    public ResponseEntity<EntityModel<UserModel>> newUser(@RequestBody UserModel user) {

        EntityModel<UserModel> entityModel = assembler.toModel(repository.save(user));
        user.setRole(roleRepository.findById(1L).orElse(null));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(value = "/users/{id}")
    public EntityModel<UserModel> oneUser(@PathVariable Long id) {

        UserModel userModel = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(userModel);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> replaceUser(@RequestBody UserModel newUserModel, @PathVariable Long id) {

        UserModel updateUser = repository.findById(id)
                .map(userModel -> {
                    userModel.setUsername(newUserModel.getUsername());
                    userModel.setRole(newUserModel.getRole());
                    userModel.setPassword(newUserModel.getPassword());
                    return repository.save(userModel);
                })
                .orElseGet(() -> {
                    newUserModel.setId(id);
                    return repository.save(newUserModel);
                });

        EntityModel<UserModel> entityModel = assembler.toModel(updateUser);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
