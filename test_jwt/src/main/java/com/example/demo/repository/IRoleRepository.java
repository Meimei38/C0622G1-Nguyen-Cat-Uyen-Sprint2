package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    @Query(
            value = " select role.id, role.name from role " +
                    " join user_role on role.id = user_role.role_id " +
                    " where user_id = :userId ", nativeQuery = true
    )
    List<Role> getListRoleByAccountId(@Param("userId") Integer userId);


    Optional<Role> findByName(String name);
}
