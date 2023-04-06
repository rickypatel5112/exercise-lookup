package com.fitness.FitnessApp.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "type",
        "muscle",
        "equipment",
        "difficulty",
        "instructions"
})
@NoArgsConstructor
@Getter
@Setter
@Generated("jsonschema2pojo")
@Entity
@Table(name = "EXERCISE")
public class Exercise {

    @JsonProperty("name")
    @Column(name = "NAME")
    @NotNull
    @Id
    private String name;

    @Column(name = "TYPE")
    @JsonProperty("type")
    private String type;

    @Column(name = "MUSCLE")
    @JsonProperty("muscle")
    private String muscle;

    @Column(name = "EQUIPMENT")
    @JsonProperty("equipment")
    private String equipment;

    @Column(name = "DIFFICULTY")
    @JsonProperty("difficulty")
    private String difficulty;

    @Column(name = "INSTRUCTIONS", columnDefinition = "TEXT")

    @JsonProperty("instructions")
    private String instructions;

    @ManyToMany(mappedBy = "exercises")
    private List<User> users = new ArrayList<>();

    public Exercise(String name, String type, String muscle, String equipment, String difficulty, String instructions) {
        this.name = name;
        this.type = type;
        this.muscle = muscle;
        this.equipment = equipment;
        this.difficulty = difficulty;
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", muscle='" + muscle + '\'' +
                ", equipment='" + equipment + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}