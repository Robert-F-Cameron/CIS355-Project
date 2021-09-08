package com.CIS355.MaintenanceTrak.Persistence.Model.Event;

import com.CIS355.MaintenanceTrak.Persistence.Model.Equipment.Equipment;
import com.CIS355.MaintenanceTrak.Persistence.Model.User.User;

import javax.persistence.*;
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
    private Date dateOpened;

    @Column(name = "dateClosed")
    private Date dateClosed;

    @OneToOne(targetEntity = User.class, mappedBy = "openedMxEvents")
    private User openedBy;

    @OneToOne(targetEntity = User.class, mappedBy = "closedMxEvents")
    private User closedBy;

    @ManyToOne(targetEntity = Equipment.class)
    private Equipment equipment;

    public MxEvent() {
    }

    public MxEvent(long id, String name, String description, Date dateOpened) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOpened = dateOpened;
    }

    public long getId() {
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

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
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

    public Equipment getEquipment(){
        return equipment;
    }
}
