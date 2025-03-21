package de.superfische.repository;

import de.superfische.model.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout, String> {
}
