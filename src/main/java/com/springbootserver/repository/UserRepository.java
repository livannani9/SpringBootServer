package com.springbootserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootserver.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
