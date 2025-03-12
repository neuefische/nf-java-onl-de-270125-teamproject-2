package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutControllerTest {

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
