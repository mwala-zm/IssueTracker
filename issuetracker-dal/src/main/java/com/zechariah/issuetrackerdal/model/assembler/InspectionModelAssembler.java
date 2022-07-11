 package com.zechariah.issuetrackerdal.model.assembler;

import com.zechariah.issuetrackerdal.controller.InspectionController;
import com.zechariah.issuetrackerdal.model.InspectionModel;
import com.zechariah.issuetrackerdal.model.enums.Status;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InspectionModelAssembler implements RepresentationModelAssembler<InspectionModel, EntityModel<InspectionModel>> {
    @Override
    public EntityModel<InspectionModel> toModel(InspectionModel inspectionModel) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<InspectionModel> inspect = EntityModel.of(inspectionModel,
                linkTo(methodOn(InspectionController.class).one(inspectionModel.getId())).withSelfRel(),
                linkTo(methodOn(InspectionController.class).all()).withRel("inspection"));

        // Conditional links based on state of the issue

        if (inspectionModel.getStatus() == Status.IN_PROGRESS) {
            inspect.add(linkTo(methodOn(InspectionController.class).cancelInspection(inspectionModel.getId())).withRel("cancel"));
            inspect.add(linkTo(methodOn(InspectionController.class).completeInspection(inspectionModel.getId())).withRel("complete"));
        }

        return inspect;
    }
}
