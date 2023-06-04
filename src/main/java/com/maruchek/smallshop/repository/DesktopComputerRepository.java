package com.maruchek.smallshop.repository;

import com.maruchek.smallshop.model.DesktopComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopComputerRepository extends JpaRepository<DesktopComputer, Long>,
        JpaSpecificationExecutor<DesktopComputer> {

}