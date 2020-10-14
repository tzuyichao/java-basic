package com.example.demo.repository;

import com.example.demo.dto.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);
}
