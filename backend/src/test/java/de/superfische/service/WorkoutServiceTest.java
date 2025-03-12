package de.superfische.service;

import de.superfische.model.IdService;
import de.superfische.model.Workout;
import de.superfische.repository.WorkoutRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class WorkoutServiceTest {

    WorkoutRepository mockWorkoutRepository = mock(WorkoutRepository.class);
    WorkoutService workoutService = new WorkoutService(mockWorkoutRepository);

    @Test
    void addWorkout() {
        // given
        IdService idService = new IdService();
        String id = idService.generateRandomID();
        String description = "My Test description";
        String workoutName = "My test workoutName";
        String imagePath = "c:/test/path/image.jpg";
        Workout workoutMocked = new Workout(id, description, workoutName, imagePath);
        when(mockWorkoutRepository.insert(workoutMocked)).thenReturn(workoutMocked);

        // when
        Workout workoutInserted = workoutService.addWorkout(description, workoutName, imagePath);

        // then
        verify(mockWorkoutRepository).insert(workoutInserted);
        assertNotNull(workoutInserted);
        assertEquals(workoutMocked.description(), workoutInserted.description());
        assertEquals(workoutMocked.workoutName(), workoutInserted.workoutName());
        assertEquals(workoutMocked.imagePath(), workoutInserted.imagePath());
        assertNotNull(workoutInserted.id());
    }
}
