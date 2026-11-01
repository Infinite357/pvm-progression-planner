package com.pvmplanner.backend.service;

import com.pvmplanner.backend.dto.SaveProfileRequest;
import com.pvmplanner.backend.model.PlayerProfile;
import com.pvmplanner.backend.repository.PlayerProfileRepository;
import org.springframework.stereotype.Service;

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

        applyProfileRequest(playerProfile, request);

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

        applyProfileRequest(playerProfile.get(), requestedProfile);

        return Optional.of(playerProfileRepository.save(playerProfile.get()));
    }

    private void applyProfileRequest(
            PlayerProfile profile, SaveProfileRequest request) {

        profile.setRsn(request.getRsn().trim());
        profile.setAttack(request.getAttack());
        profile.setDefence(request.getDefence());
        profile.setStrength(request.getStrength());
        profile.setRanged(request.getRanged());
        profile.setNecromancy(request.getNecromancy());
        profile.setMagic(request.getMagic());
        profile.setHerblore(request.getHerblore());
        profile.setSummoning(request.getSummoning());
        profile.setPrayer(request.getPrayer());

    }
}
