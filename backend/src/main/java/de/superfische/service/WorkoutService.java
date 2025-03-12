package de.superfische.service;

import de.superfische.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public void deleteWorkout(String id) {

        if (!workoutRepository.existsById(id)) {
            throw new IllegalArgumentException("Workout with ID " + id + " was not found."
            );
        }
        workoutRepository.deleteById(id);
    }
}
