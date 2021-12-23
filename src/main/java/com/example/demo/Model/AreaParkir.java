package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "areaparkir")
public class AreaParkir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /*
    1: Mobil
    2: Motor
     */
    public Integer jenis;

    public String kode;

    public String lokasi;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = GedungParkir.class)
    @JoinColumn(name = "gedung_parkir_id")
    GedungParkir gedungParkir;

    public Boolean isAvailable;

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

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
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

    public GedungParkir getGedungParkir() {
        return gedungParkir;
    }

    public void setGedungParkir(GedungParkir gedungParkir) {
        this.gedungParkir = gedungParkir;
    }
}
