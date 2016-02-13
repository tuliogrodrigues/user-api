package com.tgr.user.domain.assembler;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tgr.user.controller.UserAddressController;
import com.tgr.user.controller.UserController;
import com.tgr.user.domain.Gender;
import com.tgr.user.domain.User;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by trodrigues on 1/19/16.
 */
@Component
public class UserResourceAssembler implements ResourceAssembler<User, UserResourceAssembler.UserResource> {

    @Override
    public UserResource toResource(User entity) {
        UserResource resource = new UserResource(entity);
        resource.add(ControllerLinkBuilder.linkTo(UserController.class).slash(entity.getId()).withSelfRel());
        resource.add(ControllerLinkBuilder.linkTo(UserAddressController.class, entity.getId()).withRel("addresses"));

        return resource;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserResource extends ResourceSupport {

        private String userId;

        private String documentId;

        private String firstName;

        private String lastName;

        private String phone;

        private String email;

        private Gender gender;

        @JsonCreator
        public UserResource(@JsonProperty("user") User user) {

            this.userId = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.phone = user.getPhone();
            this.email = user.getEmail();
            this.gender = user.getGender();
            this.documentId = user.getDocumentId();
        }

        public String getUserId() {
            return userId;
        }

        public String getDocumentId() {
            return documentId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public Gender getGender() {
            return gender;
        }
    }
}
