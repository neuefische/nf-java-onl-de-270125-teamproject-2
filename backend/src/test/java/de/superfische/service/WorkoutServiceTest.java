package de.superfische.service;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class WorkoutServiceTest {

    private WorkoutRepository workoutRepository;
    private WorkoutService workoutService;

    @BeforeEach
    void setUp() {
        workoutRepository = mock(WorkoutRepository.class);
        workoutService = new WorkoutService(workoutRepository);
    }

    @Test
    void deleteWorkoutExists() {
        // GIVEN
        String workoutId = "123";
        when(workoutRepository.existsById(workoutId)).thenReturn(true);

        // WHEN
        workoutService.deleteWorkout(workoutId);

        // THEN
        verify(workoutRepository).deleteById(workoutId);
    }
    @Test
    void deleteWorkoutNotFound() {
        // GIVEN
        String workoutId = "999";
        when(workoutRepository.existsById(workoutId)).thenReturn(false);

        // WHEN + THEN
        try {
            workoutService.deleteWorkout(workoutId);
        } catch (IllegalArgumentException e) {
            verify(workoutRepository, never()).deleteById(workoutId);
        }
    }
}
