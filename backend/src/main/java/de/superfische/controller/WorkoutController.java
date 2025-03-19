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

    @PutMapping("/workout/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED) //Frage: wo fange ich hier ab, wenn es fehlschlägt
    public Workout putWorkout(@RequestBody Workout workout, @PathVariable String id)
    {
        System.out.println("Api request erfolgreich");
        return workoutService.updateWorkout(workout, id);
    }
}
