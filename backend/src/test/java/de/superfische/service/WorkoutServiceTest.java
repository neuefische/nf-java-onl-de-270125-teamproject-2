package de.superfische.service;

import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.superfische.model.IdService;
import de.superfische.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
 class WorkoutServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WorkoutRepository workoutRepository2;


    private WorkoutRepository workoutRepository;
    private WorkoutService workoutService;

    @BeforeEach
    void setUp() {
        workoutRepository = mock(WorkoutRepository.class);
        workoutService = new WorkoutService(workoutRepository);
    }

    @Test
    void deleteWorkoutExists() {
        // GIVEN
        String workoutId = "123";
        when(workoutRepository.existsById(workoutId)).thenReturn(true);

        // WHEN
        workoutService.deleteWorkout(workoutId);

        // THEN
        verify(workoutRepository).deleteById(workoutId);
    }

     @Test
     void deleteWorkoutNotFound() {
         // GIVEN
         String workoutId = "999";
         when(workoutRepository.existsById(workoutId)).thenReturn(false);

         // WHEN + THEN
         try {
             workoutService.deleteWorkout(workoutId);
         } catch (IllegalArgumentException e) {
             verify(workoutRepository, never()).deleteById(workoutId);
         }
     }

     @Test
     void addWorkout() {
         // given
         IdService idService = new IdService();
         String id = idService.generateRandomID();
         String description = "My Test description";
         String workoutName = "My test workoutName";
         String imagePath = "c:/test/path/image.jpg";
         Workout workoutMocked = new Workout(id, description, workoutName, imagePath);
         when(workoutRepository.insert(workoutMocked)).thenReturn(workoutMocked);


         // when
         Workout workoutInserted = workoutService.addWorkout(description, workoutName, imagePath);

         // then
         verify(workoutRepository).insert(workoutInserted);
         assertNotNull(workoutInserted);
         assertEquals(workoutMocked.description(), workoutInserted.description());
         assertEquals(workoutMocked.workoutName(), workoutInserted.workoutName());
         assertEquals(workoutMocked.imagePath(), workoutInserted.imagePath());
         assertNotNull(workoutInserted.id());
     }

    @Test
    void findAll() {
        //GIVEN
        Workout w1 = new Workout("1", "", "Übung-1", "übung-1-test");
        Workout w2 = new Workout("2", "", "Übung-2", "Test-Workout-2");
        when(workoutRepository.findAll()).thenReturn(List.of(w1, w2));

        //WHEN
        List<Workout> actual = workoutService.findAll();

        //THEN
        verify(workoutRepository).findAll();
        List<Workout> expected = List.of(w1, w2);
        assertEquals(expected, actual);
    }

    @Test
    void findWorkoutById_WorkoutFound_ReturnsWorkout() {
        // GIVEN
        String workoutId = "123";
        Workout workout = new Workout(workoutId, "Beschreibung", "Test Workout", "test.jpg");
        when(workoutRepository.findById(workoutId)).thenReturn(Optional.of(workout)); // Workout gefunden

        // WHEN
        Workout result = workoutService.findWorkoutById(workoutId);

        // THEN
        assertNotNull(result);
        assertEquals(workoutId, result.id());
        assertEquals("Beschreibung", result.description());
    }

    @Test
    void findWorkoutById_WorkoutNotFound_ThrowsNoSuchElementException() {
        // GIVEN
        String workoutId = "1445";
        when(workoutRepository.findById(workoutId)).thenReturn(Optional.empty()); // Workout nicht gefunden

        // WHEN + THEN
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            workoutService.findWorkoutById(workoutId);
        });

        assertEquals("Workout with id: " + workoutId + " not found!", exception.getMessage());
    }
 }
