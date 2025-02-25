package com.shoppingapplication.authservice.repository;

import com.shoppingapplication.authservice.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredentials,Integer> {
}
