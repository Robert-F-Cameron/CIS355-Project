package com.CIS355.MaintenanceTrak.Persistence.View;

import com.CIS355.MaintenanceTrak.Persistence.Model.Event.MxEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<MxEvent, Long> {
    List<MxEvent> findAll();
}
