package com.example.demo.Repository;

import com.example.demo.Model.Kendaraan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KendaraanRepository extends JpaRepository<Kendaraan, Long> {
}
