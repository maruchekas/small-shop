package com.maruchek.smallshop.repository;

import com.maruchek.smallshop.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEntityRepository extends JpaRepository<BaseEntity, Long> {
}