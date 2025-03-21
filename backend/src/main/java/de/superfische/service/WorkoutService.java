package de.superfische.service;

import de.superfische.model.IdService;
import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    // constructor
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout addWorkout(String description, String workoutName, String imagePath) {
        IdService idService = new IdService();
        String id = idService.generateRandomID();

        Workout workout = new Workout(id, description, workoutName, imagePath);
        workoutRepository.insert(workout);

        return workout;
    }

    public Workout findWorkoutById(String id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Workout with id: " + id + " not found!"));
    }

    public void deleteWorkout(String id) {

        if (!workoutRepository.existsById(id)) {
            throw new IllegalArgumentException("Workout with ID " + id + " was not found."
            );
        }
        workoutRepository.deleteById(id);
    }

    // Service method to find all workouts
    public List<Workout> findAll() {
        return workoutRepository.findAll();  // Calls the findAll() method from the repository
    }
    // Best practice: DTO + ID
    public Workout updateWorkout(Workout workout, String id) {
        //first: do findworkoutbyID to check if existent in databank, sonst exception, und dann auch daten
        // aus find by ID nutzen und dann überschreiben
        Workout workoutToUpdate = new Workout(id, workout.description(), workout.workoutName(), workout.imagePath());

        return workoutRepository.save(workoutToUpdate);
    }
}