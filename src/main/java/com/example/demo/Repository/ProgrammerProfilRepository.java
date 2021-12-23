package com.example.demo.Repository;

import com.example.demo.Model.Kendaraan;
import com.example.demo.Model.ProgrammerProfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammerProfilRepository extends JpaRepository<ProgrammerProfil, Long> {
}
