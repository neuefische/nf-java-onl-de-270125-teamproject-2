package de.superfische.controller;

import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
    void deleteWorkout_shouldReturnNotFound_whenWorkoutDoesNotExist() {

        mockMvc.perform(delete("/api/workout/{id}", "nonexistent-id"))
                .andExpect(status().isNotFound());

        assertThat(workoutRepository.existsById("nonexistent-id")).isFalse();
    }

    @Test
    @DirtiesContext
    void addWorkout() {
        // given: Nothing but the class members
        // mocked repository not needed for post mapping

        // when + then
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/api/workout")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                            {
                                 "id": "myTestID",
                                 "description": "myTestDescription",
                                 "workoutName": "myWorkoutName",
                                 "imagePath": "c:/test/path/image.jpg"
                            }
                            """))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                                 "description": "myTestDescription",
                                 "workoutName": "myWorkoutName",
                                 "imagePath": "c:/test/path/image.jpg"                            }
                            """))
                    .andExpect(jsonPath("$.id").isNotEmpty());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

}



