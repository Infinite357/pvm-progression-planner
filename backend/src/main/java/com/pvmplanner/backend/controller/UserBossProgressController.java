package com.pvmplanner.backend.controller;

import com.pvmplanner.backend.dto.CreateProgressRequest;
import com.pvmplanner.backend.model.Boss;
import com.pvmplanner.backend.repository.BossRepository;
import org.springframework.http.ResponseEntity;
import com.pvmplanner.backend.model.UserBossProgress;
import com.pvmplanner.backend.repository.UserBossProgressRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/progress")
public class UserBossProgressController {

    private final UserBossProgressRepository progressRepository;
    private final BossRepository bossRepository;

    public UserBossProgressController(UserBossProgressRepository progressRepository, BossRepository bossRepository) {
        this.progressRepository = progressRepository;
        this.bossRepository = bossRepository;
    }

    @GetMapping
    public List<UserBossProgress> findAll() {
        return progressRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<UserBossProgress> save(@RequestBody CreateProgressRequest request) {
        Optional<Boss> boss = bossRepository.findById(request.getBossId());

        if (boss.isPresent()) {
            UserBossProgress progress = new UserBossProgress();

            progress.setBoss(boss.get());
            progress.setKillCount(request.getKillCount());
            progress.setCompleted(request.isCompleted());
            progress.setConfidence(request.getConfidence());

            UserBossProgress savedProgress = progressRepository.save(progress);

            return ResponseEntity.ok(savedProgress);
        }
        return ResponseEntity.notFound().build();
    }
}



