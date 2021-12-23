package com.example.demo.Repository;

import com.example.demo.Model.GedungParkir;
import com.example.demo.Model.Sertifikat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SertifikatRepository extends JpaRepository<Sertifikat, Long> {
}
