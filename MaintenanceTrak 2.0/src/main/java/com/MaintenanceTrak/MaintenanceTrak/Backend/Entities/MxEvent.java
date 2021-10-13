package com.MaintenanceTrak.MaintenanceTrak.Backend.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Events")
public class MxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "dateOpened")
    private LocalDate dateOpened;

    @ManyToOne
    @JoinColumn(name = "opened_by_user_id")
    private User openedBy;

    @Column(name = "dateClosed")
    private LocalDate dateClosed;

    @ManyToOne
    @JoinColumn(name = "closed_by_user_id")
    private User closedBy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    public MxEvent() {
    }

    public MxEvent(String name, String description, LocalDate dateOpened, User openedBy, Equipment equipment) {
        this.name = name;
        this.description = description;
        this.dateOpened = dateOpened;
        this.openedBy = openedBy;
        this.equipment = equipment;
    }

    public MxEvent(String name, String description, LocalDate dateOpened, Equipment equipment) {
        this.name = name;
        this.description = description;
        this.dateOpened = dateOpened;
        this.equipment = equipment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDate dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDate getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDate dateClosed) {
        this.dateClosed = dateClosed;
    }

    public User getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(User openedBy) {
        this.openedBy = openedBy;
    }

    public User getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(User closedBy) {
        this.closedBy = closedBy;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public String getEquipmentName() {
        return equipment.getName();
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

}
