package com.tinyurl_system_design.tinyurl.repositories;

import com.tinyurl_system_design.tinyurl.models.TinyURLUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TinyURLUserRepository extends JpaRepository<TinyURLUser, UUID> {
    TinyURLUser findByNameAndEmail(String name, String email);

}
