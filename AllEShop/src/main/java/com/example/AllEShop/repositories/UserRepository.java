package com.example.AllEShop.repositories;

import com.example.AllEShop.entities.Role;
import com.example.AllEShop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
