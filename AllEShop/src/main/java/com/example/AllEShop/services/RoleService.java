package com.example.AllEShop.services;

import com.example.AllEShop.entities.Country;
import com.example.AllEShop.entities.Role;
import com.example.AllEShop.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface RoleService {
    Role getSimpleRole();
    Role getRoleById(Long id);
    ArrayList<Role> getAllRoles();
    void deleteRoleById(Long id);
    void saveRole(Role role);
}
