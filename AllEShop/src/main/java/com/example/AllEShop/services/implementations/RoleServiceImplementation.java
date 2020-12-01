package com.example.AllEShop.services.implementations;

import com.example.AllEShop.entities.Country;
import com.example.AllEShop.entities.Role;
import com.example.AllEShop.entities.User;
import com.example.AllEShop.repositories.CountryRepository;
import com.example.AllEShop.repositories.LanguageRepository;
import com.example.AllEShop.repositories.RoleRepository;
import com.example.AllEShop.services.CountryService;
import com.example.AllEShop.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getSimpleRole() {
        return roleRepository.getOne(1L);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public ArrayList<Role> getAllRoles() {
        ArrayList<Role> roles = (ArrayList<Role>) roleRepository.findAll();
        roles.remove(roleRepository.getOne(2L));
        return roles;
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

}
