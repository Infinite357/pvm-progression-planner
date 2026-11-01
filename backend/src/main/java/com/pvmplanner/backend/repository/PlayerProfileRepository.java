package com.pvmplanner.backend.repository;

import com.pvmplanner.backend.model.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {
}