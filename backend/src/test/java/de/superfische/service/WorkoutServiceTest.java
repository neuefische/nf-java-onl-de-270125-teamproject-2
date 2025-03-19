package de.superfische.service;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import de.superfische.service.WorkoutService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WorkoutServiceTest {

    WorkoutRepository workoutRepository = mock(WorkoutRepository.class);
    WorkoutService workoutService = new WorkoutService(workoutRepository);

    @Test
    void updateWorkout() {
        //GIVEN
        String id = "123";
        Workout workoutToUpdate = new Workout("123", "test-description", "test-name", "test-img");

        Workout updatedWorkout = new Workout("123", "test-description", "test-name", "test-img");

        when(workoutRepository.save(updatedWorkout)).thenReturn(updatedWorkout);

        //WHEN

        Workout actual = workoutService.updateWorkout(workoutToUpdate, id);

        //THEN
        verify(workoutRepository).save(updatedWorkout);

        assertEquals(updatedWorkout, actual);
    }
}
