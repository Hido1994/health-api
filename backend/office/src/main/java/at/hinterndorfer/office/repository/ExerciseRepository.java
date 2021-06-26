package at.hinterndorfer.office.repository;

import at.hinterndorfer.office.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
