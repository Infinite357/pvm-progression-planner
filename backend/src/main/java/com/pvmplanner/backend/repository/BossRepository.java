package com.pvmplanner.backend.repository;

import com.pvmplanner.backend.model.Boss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BossRepository extends JpaRepository<Boss, Long> {
}