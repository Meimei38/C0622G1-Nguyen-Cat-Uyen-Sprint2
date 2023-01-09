package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> getListRoleByAccountId(Integer userId);

}
