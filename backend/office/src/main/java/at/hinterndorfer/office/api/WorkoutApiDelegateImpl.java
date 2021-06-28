package at.hinterndorfer.office.api;

import at.hinterndorfer.office.entity.Exercise;
import at.hinterndorfer.office.model.dto.ExerciseDTO;
import at.hinterndorfer.office.model.dto.WorkoutDTO;
import at.hinterndorfer.office.model.dto.WorkoutExerciseDTO;
import at.hinterndorfer.office.repository.ExerciseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WorkoutApiDelegateImpl implements WorkoutApiDelegate {
    private final ExerciseRepository exerciseRepository;

    public WorkoutApiDelegateImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ResponseEntity<WorkoutDTO> workout(Long time) {
        if (time == null) {
            time = 600L;
        }

        Random rand = new Random();
        List<Exercise> exerciseList = exerciseRepository.findAll();

        var walkingProportion = 0.3;
        var timePerExercise = 120L;
        var walkingDuration = Math.round(time * walkingProportion);
        var numberOfExercises = (time - walkingDuration) / timePerExercise;

        List<WorkoutExerciseDTO> workoutExerciseDTOList = new ArrayList<>();

        var exercise = exerciseList.get(0);
        workoutExerciseDTOList.add(new WorkoutExerciseDTO().exercise(new ExerciseDTO()
                .name(exercise.getName()).description(exercise.getDescription()))
                .time(walkingDuration));

        for (int i = 0; i < numberOfExercises; i++) {
            exercise = exerciseList.get(rand.nextInt(exerciseList.size() - 1) + 1);
            workoutExerciseDTOList.add(new WorkoutExerciseDTO().exercise(new ExerciseDTO()
                    .name(exercise.getName())
                    .description(exercise.getDescription()))
                    .time(timePerExercise));
        }

        WorkoutDTO workoutDTO = new WorkoutDTO().exercises(workoutExerciseDTOList);

        return ResponseEntity.ok(workoutDTO);
    }
}
