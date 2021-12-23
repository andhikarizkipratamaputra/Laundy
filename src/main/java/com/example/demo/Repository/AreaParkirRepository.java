package com.example.demo.Repository;

import com.example.demo.Model.AreaParkir;
import com.example.demo.Model.GedungParkir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaParkirRepository extends JpaRepository<AreaParkir, Long> {

    @Query(value = "select ap from AreaParkir ap\n" +
            "where ap.isAvailable is true and ap.gedungParkir=?1"
    )
    List<AreaParkir> findSatuAreaParkirKosongByGedungParkir (GedungParkir gedungParkir);
}
