package de.superfische.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutTest {

    @Test
    void constructorTest() {
        // given
        IdService idService = new IdService();
        String id = idService.generateRandomID();
        String description = "My Test description";
        String workoutName = "My test workoutName";
        String imagePath = "c:/test/path/image.jpg";

        // when
        Workout workout = new Workout(id, description, workoutName, imagePath);

        // then
        assertEquals(id, workout.id());
        assertEquals(description, workout.description());
        assertEquals(workoutName, workout.workoutName());
        assertEquals(imagePath, workout.imagePath());
    }
}
