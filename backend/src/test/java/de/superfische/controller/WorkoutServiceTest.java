package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import de.superfische.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkoutServiceTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WorkoutRepository workoutRepository;

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

    @Test
    @DirtiesContext
    void findWorkoutById() throws Exception {
        // GIVEN
        Workout existingWorkout = new Workout("1", "Testdescription", "My Workoutname", "https://superfische.der-supernerd.de/image1.png");
        workoutRepository.save(existingWorkout);

        // WHEN
        mockMvc.perform(get("/api/workout/1"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {
                                "id": "1",
                                "description": "Testdescription",
                                "workoutName": "My Workoutname",
                                "imagePath": "https://superfische.der-supernerd.de/image1.png"
                            }
                        """));
    }

    @Test
    @DirtiesContext
    void findWorkoutById_WhenWorkoutNotFound_thenStatus404() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(get("/api/workout/1"))

                //THEN
                .andExpect(status().isNotFound());
    }
}
