package com.tgr.user.domain.assembler;

import com.tgr.user.controller.UserController;
import com.tgr.user.domain.User;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by trodrigues on 1/19/16.
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResource> {

    @Override
    public UserResource toResource(User entity) {
        UserResource resource = new UserResource(entity);
        resource.add(ControllerLinkBuilder.linkTo(UserController.class).slash(entity.getId()).withSelfRel());
//        resource.add(ControllerLinkBuilder.linkTo(AddressController.class, entity.id).withRel("addresses"))

        return resource;
    }
}
