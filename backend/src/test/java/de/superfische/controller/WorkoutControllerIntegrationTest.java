package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
 class WorkoutControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WorkoutRepository workoutRepository;

    @Test
    @DirtiesContext
    void deleteWorkout_shouldReturnNoContent_whenWorkoutExists() throws Exception {

        Workout workout = new Workout("test-id", "Test description", "Test Workout", "path/to/image");
        workoutRepository.save(workout);

        mockMvc.perform(delete("/api/workout/{id}", "test-id"))
                .andExpect(status().isNoContent());

        assertThat(workoutRepository.existsById("test-id")).isFalse();
    }

    @Test
    @DirtiesContext
    void deleteWorkout_shouldReturnNotFound_whenWorkoutDoesNotExist() throws Exception {

        mockMvc.perform(delete("/api/workout/{id}", "nonexistent-id"))
                .andExpect(status().isNotFound());

        assertThat(workoutRepository.existsById("nonexistent-id")).isFalse();
    }
}



