package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
