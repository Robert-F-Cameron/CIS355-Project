package com.CIS355.MaintenanceTrak.Persistence.Model.Equipment;

import javax.persistence.*;

@Entity
public class Antenna extends Equipment{

    @Column(name ="type")
    private String type;

    @Column(name="size")
    private Integer size;

    public Antenna() {
    }

    public Antenna( String equipmentId, String name, String equipmentLocation, Boolean deployed, String type, Integer size) {
        super(equipmentId, name, equipmentLocation, deployed);
        this.type = type;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
