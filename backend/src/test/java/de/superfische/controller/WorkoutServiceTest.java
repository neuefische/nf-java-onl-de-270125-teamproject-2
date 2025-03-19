package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import de.superfische.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(WorkoutController.class)
public class WorkoutServiceTest {
    @Autowired
    private MockMvc mockMvc;

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
    void testFindWorkOutByid() throws Exception {
        //GIVEN
        Workout workout = new Workout("1", "Testdescription", "My Workoutname", "https://superfische.der-supernerd.de/image1.png");

        //WHEN
        when(workoutService.findWorkoutById("1")).thenReturn(workout);

        //THEN
        mockMvc.perform(get("/api/workout/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Testdescription"))
                .andExpect(jsonPath("$.workoutName").value("My Workoutname"))
                .andExpect(jsonPath("$.imagePath").value("https://superfische.der-supernerd.de/image1.png"));

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
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.description").value("Testdescription"))
                .andExpect(jsonPath("$.workoutName").value("My Workoutname"))
                .andExpect(jsonPath("$.imagePath").value("https://superfische.der-supernerd.de/image1.png"));

    }

    @Test
    @DirtiesContext
    void findWorkoutById_WhenWorkoutNotFound_thenStatus404() throws Exception {
        //GIVEN

        //WHEN
        when(workoutService.findWorkoutById("1445")).thenThrow(new NoSuchElementException());

        //THEN
        mockMvc.perform(get("/api/workout/1445"))
                .andExpect(status().isNotFound());
    }
}
