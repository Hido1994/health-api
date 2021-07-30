package at.hinterndorfer.health.repository;

import at.hinterndorfer.health.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
