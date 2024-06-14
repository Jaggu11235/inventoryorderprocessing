package com.jagadeesh.inventorymanagementsystem.repository;


import com.jagadeesh.inventorymanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
