package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.service.WorkoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PutMapping("/workout/{id}")
    public Workout putWorkout(@RequestBody Workout workout, @PathVariable String id)
    {
        return workoutService.updateWorkout(workout, id);
    }
}
