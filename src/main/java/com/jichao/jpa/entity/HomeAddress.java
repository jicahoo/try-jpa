package com.jichao.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jichao on 2016/11/11.
 */
@Entity
public class HomeAddress {

    @Id
    @GeneratedValue
    private long id;
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", country: " + country + " }";
    }

}
