package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
