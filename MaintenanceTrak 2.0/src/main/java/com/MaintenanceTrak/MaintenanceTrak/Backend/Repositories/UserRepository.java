package com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Role;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
}
