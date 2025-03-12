package de.superfische.service;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout findWorkoutById(String id) {

        return workoutRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Workout with id: " + id + " not found!"));

    }
}
