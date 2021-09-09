package com.CIS355.MaintenanceTrak.Persistence.View;

import com.CIS355.MaintenanceTrak.Persistence.Model.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);
    List<User> findAll();
}
