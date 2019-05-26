package com.homework.rachel.services.entity;


import javax.persistence.*;

@Entity
@Table(name = "service")
@NamedQuery(name = "Service.findById", query = "select id,name from Service s where s.id=?1")
public class Service {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
