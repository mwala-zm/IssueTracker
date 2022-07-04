package com.zechariah.issuetrackerdal.controller;

import com.zechariah.issuetrackerdal.exceptions.InspectionNotFound;
import com.zechariah.issuetrackerdal.model.EquipmentModel;
import com.zechariah.issuetrackerdal.model.InspectionModel;
import com.zechariah.issuetrackerdal.model.assembler.InspectionModelAssembler;
import com.zechariah.issuetrackerdal.model.enums.Status;
import com.zechariah.issuetrackerdal.repository.EquipmentRepository;
import com.zechariah.issuetrackerdal.repository.InspectionRepository;
import com.zechariah.issuetrackerdal.repository.StateRepository;
import com.zechariah.issuetrackerdal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class InspectionController {
    @Autowired
    InspectionRepository inspectionRepository;
    EquipmentRepository equipmentRepository;
    UserRepository userRepository;
    StateRepository stateRepository;
    
    InspectionModelAssembler assembler;

    public InspectionController(InspectionRepository inspectionRepository, EquipmentRepository equipmentRepository, StateRepository stateRepository, UserRepository userRepository, InspectionModelAssembler assembler) {
        this.inspectionRepository = inspectionRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
        this.stateRepository = stateRepository;
        this.assembler = assembler;
    }

    @GetMapping(value = "/inspection")
    public CollectionModel<EntityModel<InspectionModel>> all() {

        List<EntityModel<InspectionModel>> inspection = inspectionRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inspection,
                linkTo(methodOn(InspectionController.class).all()).withSelfRel());
    }

    @GetMapping(value = "/inspection/{id}")
    public EntityModel<InspectionModel> one(@PathVariable Long id) {

        InspectionModel inspectionModel = inspectionRepository.findById(id)
                .orElseThrow(() -> new InspectionNotFound(id));

        return assembler.toModel(inspectionModel);
    }

    @PostMapping(value = "/inspection")
    public ResponseEntity<EntityModel<InspectionModel>> newInspection(@RequestBody InspectionModel inspectionModel) {

        inspectionModel.setStatus(Status.IN_PROGRESS);
        inspectionModel.setEquipment(equipmentRepository.findById(1L).orElse(null));
        inspectionModel.setUsers(userRepository.findById(1L).orElse(null));
        inspectionModel.setState(stateRepository.findById(1L).orElse(null));

        InspectionModel newInspectionModel = inspectionRepository.save(inspectionModel);

        return ResponseEntity
                .created(linkTo(methodOn(InspectionController.class).one(newInspectionModel.getId())).toUri())
                .body(assembler.toModel(newInspectionModel));
    }

    @PutMapping(value = "/inspection/{id}/complete")
    public ResponseEntity<?> completeInspection(@PathVariable Long id) {

        InspectionModel inspectionModel = inspectionRepository.findById(id)
                .orElseThrow(() -> new InspectionNotFound(id));

        if (inspectionModel.getStatus() == Status.IN_PROGRESS) {
            inspectionModel.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(assembler.toModel(inspectionRepository.save(inspectionModel)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("The Inspection is not complete " + inspectionModel.getStatus() + " status"));
    }

    @DeleteMapping(value = "/inspection/{id}/cancel")
    public ResponseEntity<?> cancelInspection(@PathVariable Long id) {

        InspectionModel inspectionModel = inspectionRepository.findById(id)
                .orElseThrow(() -> new InspectionNotFound(id));

        if (inspectionModel.getStatus() == Status.IN_PROGRESS) {
            inspectionModel.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(inspectionRepository.save(inspectionModel)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You can't remove an issue that is in the " + inspectionModel.getStatus() + " status"));
    }

}
