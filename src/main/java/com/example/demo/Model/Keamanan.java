package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "keamanan")
public class Keamanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nama;

    public String email;

    public String jkendaran;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setAlamat(String email) {
        this.email = email;
    }

    public String getJkendaran() {
        return jkendaran;
    }

    public void setJkendaran(String jkendaran) {
        this.jkendaran = jkendaran;
    }
}
