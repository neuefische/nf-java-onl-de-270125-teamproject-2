package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    @DirtiesContext
    void addWorkout() throws Exception {
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
                                 "imagePath": "c:/test/path/image.jpg"
                             }
                            """))
                    .andExpect(jsonPath("$.id").isNotEmpty());
        } catch (Exception e) {
            Assertions.fail();
        }
    }

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

    @Test
    void shouldReturnWorkoutsWhenTheyExist() throws Exception {
        Workout workout = new Workout("1", "übung-1-test", "Laufen", "");
        workoutRepository.save(workout);
        mockMvc.perform(get("/api/workout"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'1', 'workoutName':'Laufen', 'description':'übung-1-test',imagePath:''}]")); // Erwartet das gespeicherte Workout zurück
    }

    @Test
    void shouldReturnNoContentWhenNoWorkoutsPresent() throws Exception {
        mockMvc.perform(get("/api/workout"))
                .andExpect(status().isNoContent());

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
    void getMe() throws Exception {
        mockMvc.perform(get("/api/auth/me").with(oidcLogin().userInfoToken(token -> token
                                .claim("login", "testUser")
                        ))
                )
                .andExpect(status().isOk())
                .andExpect(content().string("testUser")); // Frage: Wie geht das mit JSON??
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

