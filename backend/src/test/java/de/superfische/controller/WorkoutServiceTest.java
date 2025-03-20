package de.superfische.controller;

import de.superfische.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class WorkoutServiceTest {


    private final WorkoutService workoutService = Mockito.mock(WorkoutService.class);
    private final WorkoutController workoutController = new WorkoutController(workoutService);

    @Test
    void deleteWorkoutWhenExists() {

        //GIVEN
        String workoutId = "1";

        //WHEN
        ResponseEntity<Void> response = workoutController.deleteWorkout(workoutId);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(workoutService).deleteWorkout(workoutId);
    }

    @Test
    void deleteWorkoutWhenNotExists() {

        //GIVEN
        String workoutId = "888";
        doThrow(new IllegalArgumentException("Workout not found")).when(workoutService).deleteWorkout(workoutId);
        //WHEN
        ResponseEntity<Void> response = workoutController.deleteWorkout(workoutId);

        //THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(workoutService).deleteWorkout(workoutId);
    }


}
