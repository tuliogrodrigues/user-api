package com.tgr.user.repository;

import com.tgr.user.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by trodrigues on 2/13/16.
 */
public interface AddressRepository extends MongoRepository<Address, String>,
        PagingAndSortingRepository<Address, String> {

    Page<Address> findByUserId(String userId, Pageable pageable);

    Address findByIdAndUserId(String id, String userId);
}
