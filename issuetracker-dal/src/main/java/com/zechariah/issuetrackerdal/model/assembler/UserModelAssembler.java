package com.zechariah.issuetrackerdal.model.assembler;

import com.zechariah.issuetrackerdal.controller.UserController;
import com.zechariah.issuetrackerdal.model.UserModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserModel, EntityModel<UserModel>> {

    @Override
    public EntityModel<UserModel> toModel(UserModel userModel) {

        return EntityModel.of(userModel,
                linkTo(methodOn(UserController.class).oneUser(userModel.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"));
    }
}
