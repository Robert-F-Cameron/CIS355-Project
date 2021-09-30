package com.MaintenanceTrak.MaintenanceTrak.Backend.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="equipmentId")
    private String equipmentId;

    @Column(name="name")
    private String name;

    @Column(name = "equipmentLocation")
    private String equipmentLocation;

    @Column(name = "deployed")
    private boolean deployed;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    @JoinColumn(name = "equipment_id")
    private List<MxEvent> mxEvents;

    public Equipment() {
    }

    public Equipment(Long id, String equipmentId, String name, String equipmentLocation, boolean deployed) {
        this.id = id;
        this.equipmentId = equipmentId;
        this.name = name;
        this.equipmentLocation = equipmentLocation;
        this.deployed = deployed;
    }

    public Equipment(String equipmentId, String name, String equipmentLocation, boolean deployed) {
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

    public boolean isDeployed() {
        return deployed;
    }

    public void setDeployed(boolean deployed) {
        this.deployed = deployed;
    }

    public List<MxEvent> getMxEvents() {
        return mxEvents;
    }

    public void setMxEvents(List<MxEvent> mxEvents) {
        this.mxEvents = mxEvents;
    }

    @Override
    public String toString(){
        return "Equipment [id =" +id+", name="+name+", location="+equipmentLocation+", deployed="+deployed+"]";
    }
}
