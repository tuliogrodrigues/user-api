package com.tgr.user.service;

import com.tgr.user.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by trodrigues on 2/13/16.
 */
@Service
public class UserAddressService extends AddressService {

    @Autowired
    private UserService userService;

    public Optional<Address> create(String userId, Address address) {

        return userService.getById(userId).map(u -> {
            address.setUserId(u.getId());
            return addressRepository.save(address);
        });
    }

    public Optional<Address> update(String addressId, String userId, Address address) {

        return getByIdAndUserId(addressId, userId).map(a -> {
            address.setId(a.getId());
            address.setUserId(a.getUserId());
            return addressRepository.save(address);
        });
    }

    public Boolean remove(String addressId, String userId) {

        return getByIdAndUserId(addressId, userId).map(a -> {
            addressRepository.delete(a.getId());
            return true;
        }).orElse(false);
    }
}
