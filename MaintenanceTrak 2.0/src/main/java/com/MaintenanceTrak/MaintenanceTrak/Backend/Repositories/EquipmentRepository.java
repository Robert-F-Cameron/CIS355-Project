package com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}

