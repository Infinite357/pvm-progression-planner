package com.pvmplanner.backend.controller;

import com.pvmplanner.backend.dto.CreateProgressRequest;
import com.pvmplanner.backend.model.UserBossProgress;
import com.pvmplanner.backend.service.UserBossProgressService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/progress")
public class UserBossProgressController {

    private final UserBossProgressService progressService;

    public UserBossProgressController(UserBossProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping
    public List<UserBossProgress> findAll() {
        return progressService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBossProgress> findById(@PathVariable Long id) {
        Optional<UserBossProgress> userBossProgress = progressService.findById(id);

        if (userBossProgress.isPresent()) {
            return ResponseEntity.ok(userBossProgress.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserBossProgress> save(
            @RequestBody CreateProgressRequest request) {

        if (request.getBossId() == null || request.getPlayerProfileId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserBossProgress> progress =
                progressService.createProgress(request);

        if (progress.isPresent()) {
            return ResponseEntity.ok(progress.get());
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserBossProgress> updateProgress(
            @PathVariable Long id,
            @RequestBody CreateProgressRequest request) {

        if (request.getBossId() == null || request.getPlayerProfileId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserBossProgress> updatedProgress =
                progressService.updateProgress(id, request);

        if (updatedProgress.isPresent()) {
            return ResponseEntity.ok(updatedProgress.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = progressService.deleteProgress(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/player/{playerProfileId}")
    public List<UserBossProgress> findByPlayerProfileId(@PathVariable Long playerProfileId) {
        return progressService.findByPlayerProfileId(playerProfileId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(
            IllegalArgumentException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(com.pvmplanner.backend.exception.DuplicateProgressException.class)
    public ResponseEntity<String> handleDuplicateProgress(
            com.pvmplanner.backend.exception.DuplicateProgressException exception) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(
            DataIntegrityViolationException exception) {

        Throwable cause = exception;

        while (cause != null) {
            if (cause instanceof ConstraintViolationException violation
                    && "uk_progress_player_boss".equals(
                    violation.getConstraintName())) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Progress already exists for this player and boss");
            }

            cause = cause.getCause();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unable to save progress due to a database constraint");
    }
}



