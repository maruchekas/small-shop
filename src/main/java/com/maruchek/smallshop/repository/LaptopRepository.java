package com.maruchek.smallshop.repository;

import com.maruchek.smallshop.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}