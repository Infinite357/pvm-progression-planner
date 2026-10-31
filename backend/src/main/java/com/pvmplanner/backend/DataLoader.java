package com.pvmplanner.backend;

import com.pvmplanner.backend.model.Boss;
import com.pvmplanner.backend.repository.BossRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BossRepository bossRepository;

    public DataLoader(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    @Override
    public void run(String... args) {
        if (bossRepository.count() == 0) {
            bossRepository.save(new Boss("Giant Mole", "BEGINNER"));
            bossRepository.save(new Boss("King Black Dragon", "BEGINNER"));
            bossRepository.save(new Boss("Arch-Glacor", "EARLY_PVM"));
            bossRepository.save(new Boss("Vindicta", "EARLY_PVM"));
        }
    }
}