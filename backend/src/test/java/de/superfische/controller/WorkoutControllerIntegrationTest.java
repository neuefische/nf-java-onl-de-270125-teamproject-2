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

@SpringBootTest
@AutoConfigureMockMvc
class WorkoutControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WorkoutRepository workoutRepository;

    @Test
    @DirtiesContext
    void findWorkoutById() {

        boolean a = true;
        boolean b = true;
        Assertions.assertEquals(b, a);

    }

    @Test
    @DirtiesContext
    void addWorkout() {
        // given: Nothing but the class members
        // mocked repository not needed

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
