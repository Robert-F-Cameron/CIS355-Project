package com.CIS355.MaintenanceTrak.Persistence.View;

import com.CIS355.MaintenanceTrak.Persistence.Model.Equipment.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    List<Equipment> findAll();
}
