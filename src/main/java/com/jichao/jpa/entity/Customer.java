package com.jichao.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by jichao on 2016/11/11.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private HomeAddress homeAddress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", name: " + name + ", homeAddress: " + homeAddress + " }";
    }
}
