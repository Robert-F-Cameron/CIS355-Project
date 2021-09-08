package com.CIS355.MaintenanceTrak.Persistence.Model.Equipment;

import javax.persistence.*;

@Entity
@Table(name="Cards")
public class Card extends Equipment{
    @Column(name="type")
    private String type;

    public Card(String type) {
        this.type = type;
    }

    public Card(String equipmentId, String name, String equipmentLocation, Boolean deployed, String type) {
        super(equipmentId, name, equipmentLocation, deployed);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
