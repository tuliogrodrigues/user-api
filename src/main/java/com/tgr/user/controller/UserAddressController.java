package com.tgr.user.controller;

import com.tgr.user.domain.Address;
import com.tgr.user.domain.assembler.AddressResourceAssembler;
import com.tgr.user.exceptions.BadRequestException;
import com.tgr.user.exceptions.NotFoundException;
import com.tgr.user.service.UserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trodrigues on 2/10/16.
 */
@RestController
@RequestMapping(value = "/users/{userId}/addresses", produces = "application/json")
public class UserAddressController {

    private static final Logger logger = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private AddressResourceAssembler addressResourceAssembler;

    @Autowired
    private UserAddressService userAddressService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<AddressResourceAssembler.AddressResource> list(@PathVariable String userId) {

        logger.info("Listing all user addresses. UserId= " + userId);

        return addressResourceAssembler.toResource(
                userAddressService.listByUserId(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    public AddressResourceAssembler.AddressResource get(
            @PathVariable String userId, @PathVariable String addressId) {

        logger.info("Retrieve user address to userId: " + userId);

        return userAddressService
                .getByIdAndUserId(addressId, userId)
                .map(addressResourceAssembler::toResource)
                .orElseThrow(new NotFoundException());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public AddressResourceAssembler.AddressResource create(
            @PathVariable String userId, @RequestBody Address address) {

        logger.info("Creating address " + address);

        return userAddressService
                .create(userId, address)
                .map(addressResourceAssembler::toResource)
                .orElseThrow(new BadRequestException());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{addressId}", method = RequestMethod.PUT)
    public AddressResourceAssembler.AddressResource update(
            @PathVariable String userId, @PathVariable String addressId,
            @RequestBody Address address) {

        logger.info("Updating address " + address);

        return userAddressService
                .update(addressId, userId, address)
                .map(addressResourceAssembler::toResource)
                .orElseThrow(new BadRequestException());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
    public String remove(
            @PathVariable String userId, @PathVariable String addressId) {

        logger.info("Removing address, addressId= " + addressId + "to userId= " +userId);

        if(userAddressService.remove(addressId, userId)) {
            return addressId;
        } else {
            throw new BadRequestException();
        }

    }
}
