package com.CIS355.MaintenanceTrak.Persistence.Model.Equipment;

import com.CIS355.MaintenanceTrak.Persistence.Model.Event.MxEvent;

import javax.persistence.*;

@Entity
@Table(name = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "equipmentId")
    private String equipmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "equipmentLocation")
    private String equipmentLocation;

    @Column(name = "deployed")
    private Boolean deployed;

    @Column(name="events")
    private MxEvent events;

    public Equipment() {
    }

    public Equipment(long id, String equipmentId, String name, String equipmentLocation, Boolean deployed) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.name = name;
        this.equipmentLocation = equipmentLocation;
        this.deployed = deployed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public MxEvent getEvents() {
        return events;
    }

    public void setEvents(MxEvent events) {
        this.events = events;
    }
}
