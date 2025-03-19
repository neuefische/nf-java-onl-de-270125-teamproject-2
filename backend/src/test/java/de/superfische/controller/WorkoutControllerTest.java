package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WorkoutRepository workoutRepository;

    @Test
    @DirtiesContext
    void putWorkout() throws Exception {

        //Given
        Workout existingWorkout = new Workout("1", "test-description", "test-name", "test-img");
        workoutRepository.save(existingWorkout);

        //When
        mockMvc.perform(put("/api/workout/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "description": "test-description-2",
                            "workoutName": "test-name-2",
                            "imagePath": "test-img-2"
                        }
                        """))
                //THEN
                .andExpect(status().isAccepted())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "description": "test-description-2",
                            "workoutName": "test-name-2",
                            "imagePath": "test-img-2"
                        }
                        """));
    }
}
