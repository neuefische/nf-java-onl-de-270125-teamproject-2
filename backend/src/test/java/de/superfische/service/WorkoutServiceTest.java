package de.superfische.service;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.superfische.model.IdService;
import de.superfische.model.Workout;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class WorkoutServiceTest {

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
        Workout w1 = new Workout("1", "Test-Workout-1", "Übung-1", "");
        Workout w2 = new Workout("2", "Test-Workout-2", "Übung-2", "");
        when(mockWorkoutRepository.findAll()).thenReturn(List.of(w1, w2));

        //WHEN
        List<Workout> actual = workoutService.findAll();

        //THEN
        verify(mockWorkoutRepository).findAll();
        List<Workout> expected = List.of(w1, w2);
        assertEquals(expected, actual);
    }
 }