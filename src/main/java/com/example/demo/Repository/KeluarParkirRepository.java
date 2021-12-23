package com.example.demo.Repository;

import com.example.demo.Model.KeluarParkir;
import com.example.demo.Model.ProgrammerProfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeluarParkirRepository extends JpaRepository<KeluarParkir, Long> {
}
