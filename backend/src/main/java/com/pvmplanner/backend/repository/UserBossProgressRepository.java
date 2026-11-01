package com.pvmplanner.backend.repository;

import com.pvmplanner.backend.model.UserBossProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

public interface UserBossProgressRepository extends JpaRepository<UserBossProgress, Long> {
    List<UserBossProgress> findByPlayerProfile_Id(Long playerProfileId);

    boolean existsByPlayerProfile_IdAndBoss_Id(
            Long playerProfileId, Long bossId);

    boolean existsByPlayerProfile_IdAndBoss_IdAndIdNot(
            Long playerProfileId, Long bossId, Long id);
}
