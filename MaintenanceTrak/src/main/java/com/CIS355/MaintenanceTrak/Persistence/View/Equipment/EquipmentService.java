package com.CIS355.MaintenanceTrak.Persistence.View.Equipment;

import com.CIS355.MaintenanceTrak.Persistence.Model.Equipment.Equipment;

import java.util.Optional;

public interface EquipmentService {
    void save(Equipment equipment);
    Optional <Equipment> findById(Long id);
    void delete(Long id);
    Iterable<Equipment> findAll();
}
