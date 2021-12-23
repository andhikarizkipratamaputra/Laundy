package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "kendaraan")
public class Kendaraan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /*
    1: Mobil
    2: Motor
     */
    public Integer jenis;

    public String plat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJenis() {
        return jenis;
    }

    public void setJenis(Integer jenis) {
        this.jenis = jenis;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getJenisLabel(){
        switch (getJenis()){
            case 1:
                return "Motor";
            case 2:
                return "Mobil";
            default:
                return "";
        }
    }
}
