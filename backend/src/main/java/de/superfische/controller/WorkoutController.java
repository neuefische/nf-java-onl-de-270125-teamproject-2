package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/workout")
    @ResponseStatus(HttpStatus.CREATED) // 201 - Standard, when something was created
    public Workout addWorkout(@RequestBody Workout workout) {
        return workoutService.addWorkout(workout.description(), workout.workoutName(), workout.imagePath());
    }

    @DeleteMapping("/workout/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable String id) {
        try {
            workoutService.deleteWorkout(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/workout")
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        List<Workout> workouts = workoutService.findAll();
        if (workouts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 status when no content is found
        }
        return new ResponseEntity<>(workouts, HttpStatus.OK); // 200 status with the list of workouts
    }

    @GetMapping("/workout/{id}")
    public ResponseEntity<Workout> findWorkoutById(@PathVariable String id) {

        try {
            Workout workout = workoutService.findWorkoutById(id);
            return ResponseEntity.ok(workout);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
