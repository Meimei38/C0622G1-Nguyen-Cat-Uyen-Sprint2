package com.example.demo.service.impl;

import com.example.demo.model.UserRole;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.IUserRoleRepository;
import com.example.demo.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements IUserRoleService {
    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Override
    public void save(Integer userId, Integer roleId) {
        userRoleRepository.createUserRole(userId,roleId);
    }
}
