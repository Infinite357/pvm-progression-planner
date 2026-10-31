package com.pvmplanner.backend.controller;

import com.pvmplanner.backend.model.Boss;
import com.pvmplanner.backend.repository.BossRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bosses")
public class BossController {

    private final BossRepository bossRepository;

    public BossController(BossRepository bossRepository) {
        this.bossRepository = bossRepository;
    }

    @GetMapping
    public List<Boss> getBosses() {
        return bossRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boss> getBoss(@PathVariable Long id) {
        Optional<Boss> foundBoss = bossRepository.findById(id);

        if (foundBoss.isPresent()) {
            return ResponseEntity.ok(foundBoss.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Boss createBoss(@RequestBody Boss boss) {
        return bossRepository.save(boss);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBoss(@PathVariable Long id, @RequestBody Boss boss) {
        Optional<Boss> existingBoss = bossRepository.findById(id);

        if (existingBoss.isPresent()) {
            Boss bossToUpdate = existingBoss.get();

            bossToUpdate.setName(boss.getName());
            bossToUpdate.setDifficulty(boss.getDifficulty());

            Boss savedBoss = bossRepository.save(bossToUpdate);

            return ResponseEntity.ok(savedBoss);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public Boss patchBoss(@RequestBody Boss boss) {
        return bossRepository.save(boss);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoss(@PathVariable Long id) {

        Optional<Boss> existingBoss = bossRepository.findById(id);

        if (existingBoss.isPresent()) {
            Boss bossToDelete = existingBoss.get();

            bossRepository.delete(bossToDelete);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}