package com.example.demo.Repository;

import com.example.demo.Model.GedungParkir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GedungParkirRepository extends JpaRepository<GedungParkir, Long> {
    Optional<GedungParkir> findByNama(String nama);
    Optional<GedungParkir> findById(Long id);

    @Query(value = "select p from GedungParkir p where id=(select max(id) from GedungParkir)")
    GedungParkir findMaxId();
}
