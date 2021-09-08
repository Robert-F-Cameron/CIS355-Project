package com.CIS355.MaintenanceTrak.Persistence.Model.Equipment;

import com.CIS355.MaintenanceTrak.Persistence.Model.Event.MxEvent;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "equipmentId")
    private String equipmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "equipmentLocation")
    private String equipmentLocation;

    @Column(name = "deployed")
    private Boolean deployed;

    @OneToMany(targetEntity = MxEvent.class)
    private List<MxEvent> events;

    public Equipment() {
    }

    public Equipment(String equipmentId, String name, String equipmentLocation, Boolean deployed) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.equipmentLocation = equipmentLocation;
        this.deployed = deployed;
    }

    public long getId() {
        return id;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentLocation() {
        return equipmentLocation;
    }

    public void setEquipmentLocation(String equipmentLocation) {
        this.equipmentLocation = equipmentLocation;
    }

    public Boolean getDeployed() {
        return deployed;
    }

    public void setDeployed(Boolean deployed) {
        this.deployed = deployed;
    }

    public List<MxEvent> getEvents() {
        return events;
    }

    public void setEvents(List<MxEvent> events) {
        this.events = events;
    }
}
