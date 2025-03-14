package de.superfische.controller;

import de.superfische.model.Workout;
import de.superfische.service.WorkoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
public class WorkoutServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WorkoutService workoutService;

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
    void testFindWorkoutById() throws Exception {
        //GIVEN

        //WHEN
        when(workoutService.findWorkoutById("1445")).thenThrow(new NoSuchElementException());

        //THEN
        mockMvc.perform(get("/api/workout/1445"))
                .andExpect(status().isNotFound());
    }
}
