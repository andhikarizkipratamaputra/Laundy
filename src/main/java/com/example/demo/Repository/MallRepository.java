package com.example.demo.Repository;

import com.example.demo.Model.Mall;
import com.example.demo.Model.ProgrammerProfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRepository extends JpaRepository<Mall, Long> {
}
