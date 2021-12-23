package com.example.demo.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date waktuBooking;

    public String kodeBooking;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Kendaraan.class)
    @JoinColumn(name = "kendaraaan_id")
    public Kendaraan kendaraan;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = AreaParkir.class)
    @JoinColumn(name = "areaparkir_id")
    public AreaParkir areaParkir;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWaktuBooking() {
        return waktuBooking;
    }

    public void setWaktuBooking(Date waktuBooking) {
        this.waktuBooking = waktuBooking;
    }

    public String getKodeBooking() {
        return kodeBooking;
    }

    public void setKodeBooking(String kodeBooking) {
        this.kodeBooking = kodeBooking;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public AreaParkir getAreaParkir() {
        return areaParkir;
    }

    public void setAreaParkir(AreaParkir areaParkir) {
        this.areaParkir = areaParkir;
    }
}
