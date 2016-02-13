package com.tgr.user.domain.assembler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tgr.user.controller.UserAddressController;
import com.tgr.user.domain.Address;
import com.tgr.user.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by trodrigues on 2/10/16.
 */
@Component
public class AddressResourceAssembler implements ResourceAssembler<Address, AddressResourceAssembler.AddressResource> {

    @Override
    public AddressResource toResource(Address entity) {

        AddressResource resource = new AddressResource(entity);
        resource.add(ControllerLinkBuilder.linkTo(UserAddressController.class, entity.getUserId()).slash(entity.getId()).withSelfRel());
        return resource;
    }


    public Page<AddressResource> toResource(Page<Address> entities) {

        List<AddressResource> resources = entities.getContent()
                .stream()
                .map(this::toResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class AddressResource extends ResourceSupport {

        private String addressId;

        private String label;

        private Country country;

        private String state;

        private String city;

        private String district;

        private String street;

        private String number;

        private String complement;

        private String zipcode;

        private Boolean main;

        public AddressResource(@JsonProperty("address") Address address) {
            this.addressId = address.getId();
            this.label = address.getLabel();
            this.country = address.getCountry();
            this.state = address.getState();
            this.city = address.getCity();
            this.district = address.getDistrict();
            this.street = address.getStreet();
            this.number = address.getNumber();
            this.complement = address.getComplement();
            this.zipcode = address.getZipcode();
            this.main = address.getMain();
        }

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getComplement() {
            return complement;
        }

        public void setComplement(String complement) {
            this.complement = complement;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Boolean getMain() {
            return main;
        }

        public void setMain(Boolean main) {
            this.main = main;
        }
    }
}
