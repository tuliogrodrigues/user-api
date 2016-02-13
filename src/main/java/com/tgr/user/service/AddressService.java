package com.tgr.user.service;

import com.tgr.user.domain.Address;
import com.tgr.user.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Created by trodrigues on 2/13/16.
 */
@Service
public class AddressService {

    @Autowired
    protected AddressRepository addressRepository;

    public Page<Address> listByUserId(@NotNull String userId) {

        return addressRepository.findByUserId(userId, new PageRequest(0,5));
    }

    public Optional<Address> getByIdAndUserId(String addressId, String userId) {

        return Optional.ofNullable(
                addressRepository.findByIdAndUserId(addressId, userId)
        );
    }
}
