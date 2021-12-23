package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "programmerProfil")
public class KeluarParkir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

}
