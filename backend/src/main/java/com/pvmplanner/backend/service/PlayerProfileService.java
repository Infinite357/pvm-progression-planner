package com.pvmplanner.backend.service;

import com.pvmplanner.backend.model.PlayerProfile;
import com.pvmplanner.backend.repository.PlayerProfileRepository;
import org.springframework.stereotype.Service;
import com.pvmplanner.backend.dto.SaveProfileRequest;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerProfileService {

    private final PlayerProfileRepository playerProfileRepository;

    public PlayerProfileService(PlayerProfileRepository playerProfileRepository) {
        this.playerProfileRepository = playerProfileRepository;
    }

    public PlayerProfile createProfile(SaveProfileRequest request) {
        validateProfile(request);

        PlayerProfile playerProfile = new PlayerProfile();

        playerProfile.setRsn(request.getRsn().trim());
        playerProfile.setAttack(request.getAttack());
        playerProfile.setDefence(request.getDefence());
        playerProfile.setStrength(request.getStrength());
        playerProfile.setRanged(request.getRanged());
        playerProfile.setMagic(request.getMagic());
        playerProfile.setPrayer(request.getPrayer());
        playerProfile.setNecromancy(request.getNecromancy());
        playerProfile.setHerblore(request.getHerblore());
        playerProfile.setSummoning(request.getSummoning());

        return playerProfileRepository.save(playerProfile);
    }

    private void validateSkillLevel(String skillName, int level) {
        if (level < 1) {
            throw new IllegalArgumentException(
                    skillName + " must be at least 1");
        }
    }

    public List<PlayerProfile> findAll() {
        return playerProfileRepository.findAll();
    }

    public Optional<PlayerProfile> findById(Long id) {
        return playerProfileRepository.findById(id);
    }

    private void validateProfile(SaveProfileRequest playerProfile) {
        String rsn = playerProfile.getRsn();

        if (rsn == null || rsn.isBlank()) {
            throw new IllegalArgumentException(
                    "RuneScape name cannot be blank");
        }

        validateSkillLevel("Attack", playerProfile.getAttack());
        validateSkillLevel("Strength", playerProfile.getStrength());
        validateSkillLevel("Defence", playerProfile.getDefence());
        validateSkillLevel("Ranged", playerProfile.getRanged());
        validateSkillLevel("Necromancy", playerProfile.getNecromancy());
        validateSkillLevel("Magic", playerProfile.getMagic());
        validateSkillLevel("Herblore", playerProfile.getHerblore());
        validateSkillLevel("Summoning", playerProfile.getSummoning());
        validateSkillLevel("Prayer", playerProfile.getPrayer());
    }

public Optional<PlayerProfile> updateProfile(
        Long id, SaveProfileRequest requestedProfile) {

        Optional<PlayerProfile> playerProfile = playerProfileRepository.findById(id);

        if (!playerProfile.isPresent()) {
            return Optional.empty();
        }

        validateProfile(requestedProfile);

        playerProfile.get().setRsn(requestedProfile.getRsn().trim());
        playerProfile.get().setAttack(requestedProfile.getAttack());
        playerProfile.get().setStrength(requestedProfile.getStrength());
        playerProfile.get().setDefence(requestedProfile.getDefence());
        playerProfile.get().setRanged(requestedProfile.getRanged());
        playerProfile.get().setNecromancy(requestedProfile.getNecromancy());
        playerProfile.get().setMagic(requestedProfile.getMagic());
        playerProfile.get().setHerblore(requestedProfile.getHerblore());
        playerProfile.get().setSummoning(requestedProfile.getSummoning());
        playerProfile.get().setPrayer(requestedProfile.getPrayer());

        return Optional.of(playerProfileRepository.save(playerProfile.get()));

    }
}
