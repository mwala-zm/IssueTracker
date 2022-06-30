package com.zechariah.issuetrackerdal.model.assembler;

import com.zechariah.issuetrackerdal.controller.EquipmentController;
import com.zechariah.issuetrackerdal.model.EquipmentModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EquipmentAssembler implements RepresentationModelAssembler<EquipmentModel, EntityModel<EquipmentModel>> {
    @Override
    public EntityModel<EquipmentModel> toModel(EquipmentModel machinary) {

        return EntityModel.of(machinary,
                linkTo(methodOn(EquipmentController.class).oneMachine(machinary.getId())).withSelfRel(),
                linkTo(methodOn(EquipmentController.class).deleteMachine(machinary.getId())).withSelfRel(),
                linkTo(methodOn(EquipmentController.class).getAll()).withRel("Machine"));

    }
}
