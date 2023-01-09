package com.example.demo.repository;

import com.example.demo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IUserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Modifying
    @Query(value = " insert into user_role (user_id, role_id) " +
            " values" +
            " ( :userId, :roleId ) ", nativeQuery = true)
    void createUserRole(@Param("userId") Integer userId,
                        @Param("roleId") Integer roleId);
}
