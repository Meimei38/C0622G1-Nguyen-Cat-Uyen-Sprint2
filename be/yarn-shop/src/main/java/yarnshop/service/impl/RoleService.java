package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;


    @Override
    public List<Role> getListRoleByAccountId(Integer userId) {
        return roleRepository.getListRoleByAccountId(userId);
    }
}
