package at.hinterndorfer.office.api;

import at.hinterndorfer.office.entity.Exercise;
import at.hinterndorfer.office.model.dto.ExerciseDTO;
import at.hinterndorfer.office.model.dto.WorkoutDTO;
import at.hinterndorfer.office.model.dto.WorkoutExerciseDTO;
import at.hinterndorfer.office.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutApiDelegateImpl implements WorkoutApiDelegate {
    private final ExerciseRepository exerciseRepository;

    public WorkoutApiDelegateImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ResponseEntity<WorkoutDTO> workout(Long time) {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        List<WorkoutExerciseDTO> workoutExerciseDTOList = exerciseList.stream()
                .map(exercise ->
                        new WorkoutExerciseDTO()
                                .exercise(new ExerciseDTO()
                                        .name(exercise.getName())).time(1l))
                .toList();

        WorkoutDTO workoutDTO = new WorkoutDTO().exercises(workoutExerciseDTOList);

        return ResponseEntity.ok(workoutDTO);
    }
}
