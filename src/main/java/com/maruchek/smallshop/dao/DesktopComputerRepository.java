package com.maruchek.smallshop.dao;

public interface DesktopComputerRepository extends org.springframework.data.jpa.repository.JpaRepository<com.maruchek.smallshop.model.DesktopComputer, int> ,org.springframework.data.jpa.repository.JpaSpecificationExecutor<com.maruchek.smallshop.model.DesktopComputer> {
}