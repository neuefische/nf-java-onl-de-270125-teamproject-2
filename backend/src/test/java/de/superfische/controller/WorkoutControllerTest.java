package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import de.superfische.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WorkoutRepository workoutRepository;

    private final WorkoutService workoutService = Mockito.mock(WorkoutService.class);

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
