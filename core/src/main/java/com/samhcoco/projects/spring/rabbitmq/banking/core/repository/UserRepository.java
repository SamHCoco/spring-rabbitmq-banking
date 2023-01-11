package com.samhcoco.projects.spring.rabbitmq.banking.core.repository;

import com.samhcoco.projects.spring.rabbitmq.banking.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
