package com.example.demo.Repository;

import com.example.demo.Model.GedungParkir;
import com.example.demo.Model.Keamanan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeamananRepository extends JpaRepository<Keamanan, Long> {
}
