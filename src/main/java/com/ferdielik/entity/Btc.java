package com.ferdielik.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "btc")
public class Btc implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "dollar")
    private double dollar;

    @Column(name = "bitstamp")
    private double bitstamp;

    @Column(name = "btcTurk")
    private double btcTurk;

    @Column(name = "koinim")
    private double koinim;

    @Column(name = "paribu")
    private double paribu;

    @Column(name = "date")
    private Date date;

    @Transient
    private Map<String, Double> diffs = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDollar() {
        return dollar;
    }

    public void setDollar(double dollar) {
        this.dollar = dollar;
    }

    public double getBitstamp() {
        return bitstamp;
    }

    public void setBitstamp(double bitstamp) {
        this.bitstamp = bitstamp;
    }

    public double getBtcTurk() {
        return btcTurk;
    }

    public void setBtcTurk(double btcTurk) {
        this.btcTurk = btcTurk;
    }

    public double getKoinim() {
        return koinim;
    }

    public void setKoinim(double koinim) {
        this.koinim = koinim;
    }

    public double getParibu() {
        return paribu;
    }

    public void setParibu(double paribu) {
        this.paribu = paribu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Double> getDiffs() {
        return diffs;
    }

    public void setDiffs(Map<String, Double> diffs) {
        this.diffs = diffs;
    }

    @Override
    public String toString() {
        return "Btc{" +
                "id=" + id +
                ", dollar=" + dollar +
                ", bitstamp=" + bitstamp +
                ", btcTurk=" + btcTurk +
                ", koinim=" + koinim +
                ", paribu=" + paribu +
                ", date=" + date +
                ", diffs=" + diffs +
                '}';
    }
}