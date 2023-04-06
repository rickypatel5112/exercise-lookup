package com.fitness.FitnessApp.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {

    @Column(name = "USERNAME", unique = true, nullable = false)
    @Id
    private String userName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_exercises",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_name")
    )
    private List<Exercise> exercises = new ArrayList<>();

    public List<Exercise> getExercises(){
        return exercises;
    }

    public User(String userName){
        this.userName = userName;
    }


}
