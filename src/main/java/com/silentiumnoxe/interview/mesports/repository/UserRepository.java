package com.silentiumnoxe.interview.mesports.repository;

import com.silentiumnoxe.interview.mesports.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@RepositoryRestController
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Query("from User where name = :name")
    Optional<User> findByName(String name);
}
