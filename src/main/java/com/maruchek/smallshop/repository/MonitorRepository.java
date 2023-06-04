package com.maruchek.smallshop.repository;

import com.maruchek.smallshop.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}