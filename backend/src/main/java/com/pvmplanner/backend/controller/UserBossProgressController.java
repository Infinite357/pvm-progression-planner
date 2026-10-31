package com.pvmplanner.backend.controller;

import com.pvmplanner.backend.dto.CreateProgressRequest;
import com.pvmplanner.backend.model.Boss;
import com.pvmplanner.backend.model.UserBossProgress;
import com.pvmplanner.backend.repository.BossRepository;
import com.pvmplanner.backend.repository.UserBossProgressRepository;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserBossProgress> findById(@PathVariable Long id) {
        Optional<UserBossProgress> userBossProgress = progressRepository.findById(id);

        if (userBossProgress.isPresent()) {
            return ResponseEntity.ok(userBossProgress.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserBossProgress> updateProgress(
            @PathVariable Long id,
            @RequestBody CreateProgressRequest request) {

        Optional<UserBossProgress> userBossProgress = progressRepository.findById(id);

        if (!userBossProgress.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Boss> boss = bossRepository.findById(request.getBossId());

        if (!boss.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        UserBossProgress progressToUpdate = userBossProgress.get();

        progressToUpdate.setBoss(boss.get());
        progressToUpdate.setKillCount(request.getKillCount());
        progressToUpdate.setCompleted(request.isCompleted());
        progressToUpdate.setConfidence(request.getConfidence());

        UserBossProgress updatedProgress =
                progressRepository.save(progressToUpdate);

        return ResponseEntity.ok(updatedProgress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<UserBossProgress> userBossProgress = progressRepository.findById(id);

        if (!userBossProgress.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        progressRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}



