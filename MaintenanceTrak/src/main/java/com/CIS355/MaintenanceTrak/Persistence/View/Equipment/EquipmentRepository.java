package com.CIS355.MaintenanceTrak.Persistence.View.Equipment;

import com.CIS355.MaintenanceTrak.Persistence.Model.Equipment.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
}
