package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Brand;
import com.example.AllEShop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
