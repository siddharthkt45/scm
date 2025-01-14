package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // extra methods for db related operations
    // custom query methods

    // custom finder methods
    // since findByEmail is not present normally in JpaRepository, we have to define it here
    // and the pattern to define it is findBy<FieldName> in CamelCase, and Spring will automatically
    // create the query for us... we can do this for any field in the User entity
    Optional<User> findByEmail(String email);
}
