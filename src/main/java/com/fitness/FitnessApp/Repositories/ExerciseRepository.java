package com.fitness.FitnessApp.Repositories;

import com.fitness.FitnessApp.Model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, String> {

}
