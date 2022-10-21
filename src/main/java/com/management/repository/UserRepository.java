package com.management.repository;

import com.management.entity.User;
import com.sun.istack.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(@NotNull String username);

}
