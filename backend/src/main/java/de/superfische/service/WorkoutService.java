package de.superfische.service;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout updateWorkout(Workout workout, String id) {
        Workout workoutToUpdate = new Workout(id, workout.description(), workout.workoutName(), workout.imagePath());

        return workoutRepository.save(workoutToUpdate);
    }
}
