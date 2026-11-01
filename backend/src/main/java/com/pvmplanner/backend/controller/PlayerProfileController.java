package com.pvmplanner.backend.controller;

import com.pvmplanner.backend.model.PlayerProfile;
import com.pvmplanner.backend.repository.PlayerProfileRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class PlayerProfileController {

    private final PlayerProfileRepository playerProfileRepository;

    public PlayerProfileController(PlayerProfileRepository playerProfileRepository) {
        this.playerProfileRepository = playerProfileRepository;
    }

    @GetMapping
    public List<PlayerProfile> getPlayerProfiles() {
        return playerProfileRepository.findAll();
    }

    @PostMapping
    public PlayerProfile createPlayerProfile(@RequestBody PlayerProfile playerProfile) {
        return playerProfileRepository.save(playerProfile);
    }
}