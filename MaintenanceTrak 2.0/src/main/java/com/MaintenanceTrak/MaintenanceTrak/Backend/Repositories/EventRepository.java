package com.MaintenanceTrak.MaintenanceTrak.Backend.Repositories;

import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.Equipment;
import com.MaintenanceTrak.MaintenanceTrak.Backend.Entities.MxEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<MxEvent, Long> {
    List<MxEvent> findByEquipmentId(Long equipmentId);
}
