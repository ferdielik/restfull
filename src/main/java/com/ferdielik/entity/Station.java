package com.ferdielik.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "station")
public class Station implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "town")
    private String town;

    @Column(name = "empty")
    private int empty;

    @Column(name = "bicycle")
    private int bicycle;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StationStatus status;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTown()
    {
        return town;
    }

    public void setTown(String town)
    {
        this.town = town;
    }

    public int getEmpty()
    {
        return empty;
    }

    public void setEmpty(int empty)
    {
        this.empty = empty;
    }

    public int getBicycle()
    {
        return bicycle;
    }

    public void setBicycle(int bicycle)
    {
        this.bicycle = bicycle;
    }

    public StationStatus getStatus()
    {
        return status;
    }

    public void setStatus(StationStatus status)
    {
        this.status = status;
    }

    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString()
    {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", town='" + town + '\'' +
                ", empty=" + empty +
                ", bicycle=" + bicycle +
                ", status=" + status +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}