package com.example.demo.repository;

import com.example.demo.model.Canteen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanteenRepository extends JpaRepository<Canteen, String> {
}
