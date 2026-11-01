package com.pvmplanner.backend.service;

import com.pvmplanner.backend.dto.CreateProgressRequest;
import com.pvmplanner.backend.model.Boss;
import com.pvmplanner.backend.model.PlayerProfile;
import com.pvmplanner.backend.model.UserBossProgress;
import com.pvmplanner.backend.repository.BossRepository;
import com.pvmplanner.backend.repository.PlayerProfileRepository;
import com.pvmplanner.backend.repository.UserBossProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBossProgressService {

    private final UserBossProgressRepository progressRepository;
    private final BossRepository bossRepository;
    private final PlayerProfileRepository playerProfileRepository;

    public UserBossProgressService(
            UserBossProgressRepository progressRepository,
            BossRepository bossRepository,
            PlayerProfileRepository playerProfileRepository) {

        this.progressRepository = progressRepository;
        this.bossRepository = bossRepository;
        this.playerProfileRepository = playerProfileRepository;
    }

    public List<UserBossProgress> findByPlayerProfileId(Long playerProfileId) {
        return progressRepository.findByPlayerProfile_Id(playerProfileId);
    }

    public Optional<UserBossProgress> createProgress(CreateProgressRequest request) {

        if (request.getKillCount() < 0) {
            throw new IllegalArgumentException("Kill count cannot be negative");
        }

        Optional<Boss> boss = bossRepository.findById(request.getBossId());
        Optional<PlayerProfile> playerProfile = playerProfileRepository.findById(request.getPlayerProfileId());

        if (!boss.isPresent() || !playerProfile.isPresent()) {
            return Optional.empty();
        }

        boolean alreadyExists =
                progressRepository.existsByPlayerProfile_IdAndBoss_Id(
                        request.getPlayerProfileId(),
                        request.getBossId());

        if (alreadyExists) {
            throw new IllegalArgumentException("Progress already exists for this player and boss");
        }

        UserBossProgress progress = new UserBossProgress();
        progress.setBoss(boss.get());
        progress.setPlayerProfile(playerProfile.get());

        progress.setConfidence(request.getConfidence());
        progress.setKillCount(request.getKillCount());
        progress.setCompleted(request.isCompleted());

        return Optional.of(progressRepository.save(progress));
    }

    public Optional<UserBossProgress> updateProgress(
            Long id, CreateProgressRequest request) {

        if (request.getKillCount() < 0) {
            throw new IllegalArgumentException("Kill count cannot be negative");
        }

        Optional<UserBossProgress> progress = progressRepository.findById(id);

        if (!progress.isPresent()) {
            return Optional.empty();
        }

        boolean alreadyExists =
                progressRepository.existsByPlayerProfile_IdAndBoss_IdAndIdNot(
                        request.getPlayerProfileId(),
                        request.getBossId(),
                        id);

        if (alreadyExists) {
            throw new IllegalArgumentException("Progress already exists for this player and boss");
        }

        Optional<Boss> boss = bossRepository.findById(request.getBossId());
        Optional<PlayerProfile> playerProfile = playerProfileRepository.findById(request.getPlayerProfileId());

        if (!boss.isPresent() || !playerProfile.isPresent()) {
            return Optional.empty();
        }

        UserBossProgress progressToUpdate = progress.get();

        progressToUpdate.setBoss(boss.get());
        progressToUpdate.setPlayerProfile(playerProfile.get());
        progressToUpdate.setConfidence(request.getConfidence());
        progressToUpdate.setKillCount(request.getKillCount());
        progressToUpdate.setCompleted(request.isCompleted());

        return Optional.of(progressRepository.save(progressToUpdate));
    }

    public boolean deleteProgress(Long id) {
        Optional<UserBossProgress> progress = progressRepository.findById(id);

        if (!progress.isPresent()) {
            return false;
        }
        progressRepository.deleteById(id);
        return true;
    }

    public List<UserBossProgress> findAll() {
        return progressRepository.findAll();
    }

    public Optional<UserBossProgress> findById(Long id) {
        return progressRepository.findById(id);
    }


}