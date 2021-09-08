package com.CIS355.MaintenanceTrak.Persistence.Model.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    //TODO: Figure out how to properly associate roles with Users
    //private List<User> users;
}
