package com.CIS355.MaintenanceTrak.Persistence.Model.User;

import com.CIS355.MaintenanceTrak.Persistence.Model.Event.MxEvent;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="password")
    private String password;

    @ManyToOne(targetEntity = MxEvent.class)
    private List<MxEvent> openedMxEvents;

    @ManyToOne(targetEntity = MxEvent.class)
    private List<MxEvent> closedMxEvents;

    //TODO: Figure out how to add roles for each user.
//    private Role role;

    public User() {
    }

    public User(long id, String username, String firstName, String lastName, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
