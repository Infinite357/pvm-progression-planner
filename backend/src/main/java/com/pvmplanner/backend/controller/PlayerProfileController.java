package com.pvmplanner.backend.controller;

import com.pvmplanner.backend.model.PlayerProfile;
import com.pvmplanner.backend.service.PlayerProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pvmplanner.backend.dto.SaveProfileRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class PlayerProfileController {

    private final PlayerProfileService playerProfileService;

    public PlayerProfileController(PlayerProfileService playerProfileService) {
        this.playerProfileService = playerProfileService;
    }

    @GetMapping
    public List<PlayerProfile> getPlayerProfiles() {
        return playerProfileService.findAll();
    }

    @PostMapping
    public PlayerProfile createPlayerProfile(
            @RequestBody SaveProfileRequest request) {

        return playerProfileService.createProfile(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerProfile> getPlayerProfile(@PathVariable Long id) {

        Optional<PlayerProfile> playerProfile = playerProfileService.findById(id);

        if (playerProfile.isPresent()) {
            return ResponseEntity.ok(playerProfile.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerProfile> updateProfile(
            @PathVariable Long id,
            @RequestBody SaveProfileRequest requestedProfile) {

        Optional<PlayerProfile> updatedProfile =
                playerProfileService.updateProfile(id, requestedProfile);

        if (updatedProfile.isPresent()) {
            return ResponseEntity.ok(updatedProfile.get());
        }

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}